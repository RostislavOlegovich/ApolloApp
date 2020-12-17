package com.slavainc.apollo

import BookTripMutation
import CancelTripMutation
import LaunchDetailsQuery
import LaunchListQuery
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await

class ApolloManagerImpl(private val apolloInitializer: ApolloInitializer) : ApolloManager {

    override suspend fun login() {
//        apolloInitializer.apolloClient().mutate()
        TODO("Not yet implemented")
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
}