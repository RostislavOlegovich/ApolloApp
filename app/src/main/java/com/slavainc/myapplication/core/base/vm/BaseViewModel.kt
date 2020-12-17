package com.slavainc.myapplication.core.base.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slavainc.myapplication.utils.Resource
import com.slavainc.utils.ResponseWrapper
import kotlinx.coroutines.*
import org.koin.core.KoinComponent

abstract class BaseViewModel : ViewModel(), KoinComponent, CoroutineScope {

    private val supervisorJob = SupervisorJob()

    override val coroutineContext = Dispatchers.Main.immediate + supervisorJob +
            CoroutineExceptionHandler { _, e -> onError(e) }

    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancelChildren()
    }

    open fun onError(exception: Throwable) {
        Log.e("ERROR", "${exception.printStackTrace()}")
    }

    fun <T> request(
        liveData: MutableLiveData<Resource<T>>,
        request: suspend () -> ResponseWrapper<T>
    ) {
        liveData.postValue(Resource.loading())
        this.viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = request.invoke()
                if (response.data != null) {
                    liveData.postValue(Resource.success(response.data))
                } else if (response.error != null) {
                    liveData.postValue(Resource.error(response.error))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                liveData.postValue(Resource.error(e.message))
            }
        }
    }
}