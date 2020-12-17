package com.slavainc.mapper

import LaunchDetailsQuery
import com.slavainc.entity.Launch

class LaunchMapper(
    private val rocketMapper: RocketMapper,
    private val missionMapper: MissionMapper
) : Mapper<LaunchDetailsQuery.Data, Launch> {

    override fun from(source: LaunchDetailsQuery.Data?): Launch? =
        source?.launch?.run {
            Launch(
                id,
                site,
                missionMapper from mission,
                rocketMapper from rocket,
                isBooked
            )
        }
}