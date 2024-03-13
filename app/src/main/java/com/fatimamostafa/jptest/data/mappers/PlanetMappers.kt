package com.fatimamostafa.jptest.data.mappers

import com.fatimamostafa.jptest.data.local.PlanetEntity
import com.fatimamostafa.jptest.data.remote.PlanetDto
import com.fatimamostafa.jptest.domain.Planet

fun PlanetDto.toPlanetEntity(): PlanetEntity {
    return PlanetEntity(
        name = name,
        uid = uid,
        url = url,
    )
}

fun PlanetEntity.toPlanet(): Planet {
    return Planet(
        name = name,
        url = url,
        uid = uid
    )
}