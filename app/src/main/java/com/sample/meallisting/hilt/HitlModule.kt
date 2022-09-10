package com.sample.meallisting.hilt

import com.sample.meallisting.commons.BASE_URL
import com.sample.meallisting.data.remote.APIService
import com.sample.meallisting.data.repository.MealRepositoryImpl
import com.sample.meallisting.domain.repository.MealRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object HiltModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): APIService {
        return retrofit.create(APIService::class.java)
    }

    @Provides
    fun provideMealRepository(apiService: APIService): MealRepository {
        return MealRepositoryImpl(apiService)
    }

}