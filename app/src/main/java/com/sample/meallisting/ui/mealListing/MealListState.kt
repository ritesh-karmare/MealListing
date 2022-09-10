package com.sample.meallisting.ui.mealListing

import com.sample.meallisting.domain.model.Meal

data class MealListState(
    val mealList: List<Meal>? = null,
    val loading: Boolean = false,
    val error: String? = null
)
