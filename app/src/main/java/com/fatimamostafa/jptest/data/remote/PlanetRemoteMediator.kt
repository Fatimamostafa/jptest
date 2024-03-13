package com.fatimamostafa.jptest.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.fatimamostafa.jptest.data.local.PlanetDatabase
import com.fatimamostafa.jptest.data.local.PlanetEntity
import com.fatimamostafa.jptest.data.mappers.toPlanetEntity
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class PlanetRemoteMediator(
    private val planetDb: PlanetDatabase,
    private val planetApi: PlanetApi
) : RemoteMediator<Int, PlanetEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PlanetEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )

                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        1
                    } else {
                        ((lastItem.uid.toInt() / state.config.pageSize)) + 1
                    }
                }
            }

            val responsePlanet = planetApi.getPlanets()

            planetDb.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    planetDb.dao.clearAll()
                }
                val planetEntities = responsePlanet.results.map {
                    it.toPlanetEntity()
                }
                planetDb.dao.upsertAll(planetEntities)
            }

            //Turned off pagination
            MediatorResult.Success(
                endOfPaginationReached = true
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}
