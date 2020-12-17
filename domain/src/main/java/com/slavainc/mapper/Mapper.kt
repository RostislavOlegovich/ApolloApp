package com.slavainc.mapper

import org.koin.core.KoinComponent

interface Mapper<in Source, out Destination> : KoinComponent {
    infix fun from(source: Source?): Destination?
}