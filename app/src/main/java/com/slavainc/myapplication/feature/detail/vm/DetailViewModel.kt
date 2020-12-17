package com.slavainc.myapplication.feature.detail.vm

import androidx.lifecycle.MutableLiveData
import com.slavainc.entity.Launch
import com.slavainc.myapplication.core.base.vm.BaseViewModel
import com.slavainc.myapplication.utils.Resource
import com.slavainc.myapplication.utils.SingleLiveEvent
import com.slavainc.repository.MainRepository

class DetailViewModel(private val mainRepository: MainRepository) : BaseViewModel() {

    val launchDetailLD = MutableLiveData<Resource<Launch>>()

    val tripLD = SingleLiveEvent<Resource<Any>>()

    fun launchDetail(launchId: String) {
        request(launchDetailLD) {
            mainRepository.launchDetails(launchId)
        }
    }

    fun checkTrip(launchId: String, isBooked: Boolean) {
        request(tripLD) {
            mainRepository.checkTrip(launchId, isBooked)
        }
    }
}