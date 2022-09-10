package com.sample.meallisting.data.model

import com.sample.meallisting.domain.model.Meal

data class MealsDTO(
    val meals: List<MealDTO>
)

data class MealDTO(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String
)

fun MealDTO.toDomainMeal(): Meal {
    return Meal(
        id = this.idMeal,
        mealName = this.strMeal,
        mealThumb = this.strMealThumb
    )
}