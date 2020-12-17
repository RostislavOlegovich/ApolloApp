package com.slavainc.repository

import com.slavainc.apollo.ApolloManager
import com.slavainc.mapper.LaunchListMapper
import com.slavainc.mapper.LaunchMapper
import com.slavainc.utils.handle
import org.koin.core.inject

class MainRepositoryImpl(private val apolloManager: ApolloManager) : MainRepository {

    private val launchMapper: LaunchMapper by inject()
    private val launchListMapper: LaunchListMapper by inject()

    override suspend fun login() {
        TODO("Not yet implemented")
    }

    override suspend fun checkTrip(launchId: String, isBooked: Boolean) =
        apolloManager.checkTrip(launchId, isBooked).handle { Any() }

    override suspend fun launchList(cursor: String?) =
        apolloManager.launchList(cursor).handle { launchListMapper from it }

    override suspend fun launchDetails(launchId: String) =
        apolloManager.launchDetails(launchId).handle { launchMapper from it }
}