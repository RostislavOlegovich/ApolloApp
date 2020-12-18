package com.slavainc.apollo

import LaunchDetailsQuery
import LaunchListQuery
import LoginMutation
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.Response
import kotlinx.coroutines.flow.Flow
import org.koin.core.KoinComponent

interface ApolloManager : KoinComponent {

    suspend fun login(email: String?): Response<LoginMutation.Data>

    suspend fun checkTrip(launchId: String, isBooked: Boolean): Response<out Operation.Data>

    suspend fun launchList(cursor: String?): Response<LaunchListQuery.Data>

    suspend fun launchDetails(launchId: String): Response<LaunchDetailsQuery.Data>

    suspend fun subscribe(callback: (Response<TripsBookedSubscription.Data>) -> Unit)
}