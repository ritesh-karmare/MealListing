package com.sample.meallisting.domain.repository

import com.sample.meallisting.data.model.MealsDTO

interface MealRepository {

    suspend fun getMealsForCategory(category: String): MealsDTO

}