package com.sample.meallisting.data.remote

import com.sample.meallisting.commons.MEAL_LISTING_API
import com.sample.meallisting.data.model.MealsDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET(MEAL_LISTING_API)
    suspend fun getMealsByCategory(@Query("c") categoryName: String): MealsDTO

}