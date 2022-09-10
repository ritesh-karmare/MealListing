package com.sample.meallisting.domain.usecase

import com.sample.meallisting.data.model.toDomainMeal
import com.sample.meallisting.data.remote.Resource
import com.sample.meallisting.data.remote.flowApiCall
import com.sample.meallisting.domain.model.Meal
import com.sample.meallisting.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ListMealsUseCase @Inject constructor(private val repository: MealRepository) {

    operator fun invoke(category: String): Flow<Resource<List<Meal>>> {
        return flowApiCall {
            val data = repository.getMealsForCategory(category)
            data.meals.map { it.toDomainMeal() }
        }
    }
}