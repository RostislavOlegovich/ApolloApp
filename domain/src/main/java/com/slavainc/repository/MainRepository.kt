package com.slavainc.repository

import com.slavainc.entity.Launch
import com.slavainc.entity.Launches
import com.slavainc.utils.ResponseWrapper


interface MainRepository : BaseRepository {

    suspend fun login(email: String?): ResponseWrapper<String>

    suspend fun checkTrip(launchId: String, isBooked: Boolean): ResponseWrapper<Any>

    suspend fun launchList(cursor: String?): ResponseWrapper<Launches>

    suspend fun launchDetails(launchId: String): ResponseWrapper<Launch>

    suspend fun subscribe(callback: (ResponseWrapper<Int>) -> Unit)
}