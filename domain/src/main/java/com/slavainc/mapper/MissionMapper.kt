package com.slavainc.mapper

import LaunchDetailsQuery
import com.slavainc.entity.Mission

class MissionMapper : Mapper<LaunchDetailsQuery.Mission, Mission> {

    override fun from(source: LaunchDetailsQuery.Mission?): Mission? =
        source?.run { Mission(name, missionPatch) }
}