package com.slavainc.myapplication.feature.main.vm

import androidx.lifecycle.MutableLiveData
import com.slavainc.entity.Launches
import com.slavainc.myapplication.core.base.vm.BaseViewModel
import com.slavainc.myapplication.utils.Resource
import com.slavainc.repository.MainRepository

class MainViewModel(private val mainRepository: MainRepository) : BaseViewModel() {

    val launchesLD = MutableLiveData<Resource<Launches>>()

    fun launchList(cursor: String?) {
        request(launchesLD) {
            mainRepository.launchList(cursor)
        }
    }

}