package com.sample.meallisting.ui.mealListing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.meallisting.data.remote.Resource
import com.sample.meallisting.domain.usecase.ListMealsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MealListingViewModel @Inject constructor(private val listMealsUseCase: ListMealsUseCase) :
    ViewModel() {

    private val _mealLiveData = MutableStateFlow(MealListState())
    val mealLiveData = _mealLiveData.asStateFlow()


    fun getMealByCategory(categoryName: String = "Chicken") {

        listMealsUseCase(categoryName)
            .onEach {

                when (it) {
                    is Resource.Loading ->
                        _mealLiveData.value = MealListState(loading = true)

                    is Resource.Error ->
                        _mealLiveData.value = MealListState(error = it.errorMessage)

                    is Resource.Success ->
                        _mealLiveData.value = MealListState(mealList = it.value)
                }

            }.launchIn(viewModelScope)
    }
}