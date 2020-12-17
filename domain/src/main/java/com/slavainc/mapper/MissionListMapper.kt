package com.slavainc.mapper

import LaunchListQuery
import com.slavainc.entity.Mission

class MissionListMapper : Mapper<LaunchListQuery.Mission, Mission> {

    override fun from(source: LaunchListQuery.Mission?): Mission? =
        source?.run { Mission(name, missionPatch) }
}