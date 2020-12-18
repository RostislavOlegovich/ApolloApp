package com.slavainc.apollo

import BookTripMutation
import CancelTripMutation
import LaunchDetailsQuery
import LaunchListQuery
import LoginMutation
import TripsBookedSubscription
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.coroutines.toFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.retryWhen

class ApolloManagerImpl(private val apolloInitializer: ApolloInitializer) : ApolloManager {

    override suspend fun login(email: String?): Response<LoginMutation.Data> {
        return apolloInitializer.apolloClient().mutate(LoginMutation(email = Input.fromNullable(email))).await()
    }

    override suspend fun checkTrip(launchId: String, isBooked: Boolean): Response<out Operation.Data> {
        val mutation = if (isBooked) {
            CancelTripMutation(launchId)
        } else {
            BookTripMutation(launchId)
        }
        return apolloInitializer.apolloClient().mutate(mutation).await()
    }

    override suspend fun launchList(cursor: String?): Response<LaunchListQuery.Data> {
        return apolloInitializer.apolloClient().query(LaunchListQuery(cursor = Input.fromNullable(cursor))).await()
    }

    override suspend fun launchDetails(launchId: String): Response<LaunchDetailsQuery.Data> {
        return apolloInitializer.apolloClient().query(LaunchDetailsQuery(launchId)).await()
    }

    @ExperimentalCoroutinesApi
    override suspend fun subscribe(callback: (Response<TripsBookedSubscription.Data>) -> Unit) =
        apolloInitializer.apolloClient().subscribe(TripsBookedSubscription()).toFlow()
            .retryWhen { _, attempt ->
                delay(attempt * 1000)
                true
            }
            .collect {
                callback.invoke(it)
            }

}