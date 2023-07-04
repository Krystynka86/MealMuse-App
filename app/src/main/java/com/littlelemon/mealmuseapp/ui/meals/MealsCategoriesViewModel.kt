package com.littlelemon.mealmuseapp.ui.meals

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.littlelemon.model.MealsRepository
import com.littlelemon.model.response.MealResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MealsCategoriesViewModel(private val repository: MealsRepository = MealsRepository.getInstance()) :
    ViewModel() {

    init {
        Log.d("TAG_COROUTINES", "We are about to launch a coroutine")
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("TAG_COROUTINES", "We have launched a coroutine")
            val meals = getMeals()
            Log.d("TAG_COROUTINES", "We have received the async data")
            mealsState.value = meals
        }
        Log.d("TAG_COROUTINES", "Other work")
    }

    val mealsState: MutableState<List<MealResponse>> = mutableStateOf(emptyList<MealResponse>())

    private suspend fun getMeals(): List<MealResponse> {
        return repository.getMeals().categories
    }
}
