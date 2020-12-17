package com.slavainc.apollo

import LaunchDetailsQuery
import LaunchListQuery
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.Response
import org.koin.core.KoinComponent

interface ApolloManager : KoinComponent {

    suspend fun login()

    suspend fun checkTrip(launchId: String, isBooked: Boolean): Response<out Operation.Data>

    suspend fun launchList(cursor: String?): Response<LaunchListQuery.Data>

    suspend fun launchDetails(launchId: String): Response<LaunchDetailsQuery.Data>
}