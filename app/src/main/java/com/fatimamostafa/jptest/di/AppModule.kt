package com.fatimamostafa.jptest.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
 import androidx.paging.PagingConfig
import androidx.room.Room
import com.fatimamostafa.jptest.data.local.PlanetDatabase
import com.fatimamostafa.jptest.data.local.PlanetEntity
import com.fatimamostafa.jptest.data.remote.PlanetApi
import com.fatimamostafa.jptest.data.remote.PlanetRemoteMediator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePlanetDatabase(@ApplicationContext context: Context): PlanetDatabase {
        return Room.databaseBuilder(
            context,
            PlanetDatabase::class.java,
            "planets.db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providePlanetApi(): PlanetApi {
        return Retrofit.Builder()
            .baseUrl(PlanetApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun providePlanetPager(
        planetDb: PlanetDatabase,
        planetApi: PlanetApi
    ): Pager<Int, PlanetEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = PlanetRemoteMediator(
                planetDb = planetDb,
                planetApi = planetApi
            ),
            pagingSourceFactory = {
                planetDb.dao.pagingSource()
            }
        )
    }
}