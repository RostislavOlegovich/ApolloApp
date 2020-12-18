package com.slavainc.myapplication.feature.main.vm

import androidx.lifecycle.MutableLiveData
import com.slavainc.entity.Launches
import com.slavainc.myapplication.core.base.vm.BaseViewModel
import com.slavainc.myapplication.utils.Resource
import com.slavainc.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val mainRepository: MainRepository) : BaseViewModel() {

    val launchesLD = MutableLiveData<Resource<Launches>>()

    val subscribesLD = MutableLiveData<Resource<Int>>()

    fun launchList(cursor: String?) {
        request(launchesLD) {
            mainRepository.launchList(cursor)
        }
    }

    fun subscribe() {
        launch(Dispatchers.IO) {
            mainRepository.subscribe { response ->
                try {
                    if (response.data != null) {
                        subscribesLD.postValue(Resource.success(response.data))
                    } else if (response.error != null) {
                        subscribesLD.postValue(Resource.error(response.error))
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    subscribesLD.postValue(Resource.error(e.message))
                }
            }
        }
    }
}