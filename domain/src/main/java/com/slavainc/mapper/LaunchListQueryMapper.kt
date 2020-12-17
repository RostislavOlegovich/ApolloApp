package com.slavainc.mapper

import LaunchListQuery
import com.slavainc.entity.Launch

class LaunchListQueryMapper(
    private val missionListMapper: MissionListMapper
) : Mapper<LaunchListQuery.Launch, Launch> {

    override fun from(source: LaunchListQuery.Launch?): Launch? =
        source?.run {
            Launch(
                id,
                site,
                missionListMapper from mission
            )
        }
}