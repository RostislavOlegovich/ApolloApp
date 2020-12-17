package com.slavainc.entity

data class Launches(
    val cursor: String? = null,
    val hasMore: Boolean? = null,
    val launches: List<Launch?>? = null
)
