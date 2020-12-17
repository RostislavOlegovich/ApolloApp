package com.slavainc.myapplication.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

inline fun <reified L : LiveData<T>, T> LifecycleOwner.observe(
    liveData: LiveData<T>,
    noinline block: (T) -> (Unit)
) {
    liveData.observe(this, Observer(block))
}
