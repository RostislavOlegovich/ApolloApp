package com.slavainc.myapplication.feature.login.vm

import com.slavainc.myapplication.core.base.vm.BaseViewModel
import com.slavainc.myapplication.utils.Resource
import com.slavainc.myapplication.utils.SingleLiveEvent
import com.slavainc.repository.MainRepository

class LoginViewModel(private val mainRepository: MainRepository) : BaseViewModel() {

    val loginLD = SingleLiveEvent<Resource<String>>()

    fun login(launchId: String) {
        request(loginLD) {
            mainRepository.login(launchId)
        }
    }
}