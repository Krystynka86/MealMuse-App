package com.littlelemon.mealmuseapp.ui.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.littlelemon.mealmuseapp.ui.details.MealDetailScreen
import com.littlelemon.mealmuseapp.ui.details.MealDetailsViewModel
import com.littlelemon.mealmuseapp.ui.meals.MealsCategoriesScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MealMuseAppTheme {
                FoodiezApp()
            }
        }
    }
}

@Composable
private fun FoodiezApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "destination_meals_list") {
        composable(route = "destination_meals_list") {
            MealsCategoriesScreen { navigationMealId ->
                navController.navigate("destination_meals_details/$navigationMealId")
            }
        }
        composable(
            route = "destination_meals_details/{meal_category_id}",
            arguments = listOf(
                navArgument("meal_category_id") {
                    type = NavType.StringType
                },
            ),
        ) {
            val viewModel: MealDetailsViewModel = viewModel()
            MealDetailScreen(viewModel.mealState.value)
        }
    }
}
