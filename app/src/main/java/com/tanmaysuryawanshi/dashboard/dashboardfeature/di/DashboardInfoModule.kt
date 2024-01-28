package com.tanmaysuryawanshi.dashboard.dashboardfeature.di

import com.tanmaysuryawanshi.dashboard.dashboardfeature.data.remote.api.DashboardApi
import com.tanmaysuryawanshi.dashboard.dashboardfeature.data.repository_impl.DashboardRepositoryImpl
import com.tanmaysuryawanshi.dashboard.dashboardfeature.domain.repository.DashboardRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DashboardInfoModule {



    @Provides
    @Singleton
    fun provideWordInfoRepository(

        api: DashboardApi
    ): DashboardRepository {
        return DashboardRepositoryImpl(api)
    }



    @Provides
    @Singleton
    fun providesDashboardApi(): DashboardApi {
        return Retrofit.Builder()
            .baseUrl(DashboardApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DashboardApi::class.java)
    }

}