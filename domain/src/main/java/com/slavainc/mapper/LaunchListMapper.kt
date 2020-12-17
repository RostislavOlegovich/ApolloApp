package com.slavainc.mapper

import LaunchListQuery
import com.slavainc.entity.Launches

class LaunchListMapper(
    private val launchListQueryMapper: LaunchListQueryMapper
) : Mapper<LaunchListQuery.Data, Launches> {

    override fun from(source: LaunchListQuery.Data?): Launches? =
        source?.launches?.run {
            Launches(
                cursor,
                hasMore,
                launches.map { launchListQueryMapper from it }
            )
        }
}