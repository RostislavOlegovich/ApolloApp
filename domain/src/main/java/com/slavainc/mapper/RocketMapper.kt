package com.slavainc.mapper

import LaunchDetailsQuery
import com.slavainc.entity.Rocket

class RocketMapper : Mapper<LaunchDetailsQuery.Rocket, Rocket> {

    override fun from(source: LaunchDetailsQuery.Rocket?) = source?.run { Rocket(name, type) }
}