package com.littlelemon.model

import com.littlelemon.model.api.MealsWebService
import com.littlelemon.model.response.MealResponse
import com.littlelemon.model.response.MealsCategoriesResponse
import kotlinx.coroutines.internal.synchronized

class MealsRepository(private val webService: MealsWebService = MealsWebService()) {

    private var cachedMeals = listOf<MealResponse>()

    suspend fun getMeals(): MealsCategoriesResponse {
        val response = webService.getMeals()
        cachedMeals = response.categories
        return response
    }
    fun getMeal(id: String): MealResponse? {
        return cachedMeals.firstOrNull() {
            it.id == id
        }
    }
    companion object {
        @Volatile
        private var instance: MealsRepository? = null

        fun getInstance() = instance ?: kotlin.synchronized(this) {
            instance ?: MealsRepository().also { instance = it }
        }
    }
}
