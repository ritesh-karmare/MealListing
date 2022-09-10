package com.sample.meallisting.data.repository

import com.sample.meallisting.data.model.MealsDTO
import com.sample.meallisting.data.remote.APIService
import com.sample.meallisting.domain.repository.MealRepository

class MealRepositoryImpl(private val apiService: APIService) : MealRepository {

    override suspend fun getMealsForCategory(category: String): MealsDTO {
        return apiService.getMealsByCategory(category)
    }

}