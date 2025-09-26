package com.mysecondapp.basicapifetch.Presentation.routes

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mysecondapp.basicapifetch.Presentation.Screens.DescriptionScreen
import com.mysecondapp.basicapifetch.Presentation.Screens.HomeScreen
import com.mysecondapp.basicapifetch.Presentation.ViewModel.MyViewModel
import com.mysecondapp.basicapifetch.Data.models.ApiRes_Coffee_hotItem

@Composable
fun AppNavigation ( viewModel: MyViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Homescreen){
        composable<Homescreen> {
            HomeScreen(navController, viewModel)
        }
        composable<Descriptionscreen> {
            val apidata = ApiRes_Coffee_hotItem(
                description = it.arguments?.getString("description"),
                id = it.arguments?.getInt("id"),
                image = it.arguments?.getString("image"),
                ingredients = it.arguments?.getStringArrayList("ingredients"),
                title = it.arguments?.getString("title")
            )
            DescriptionScreen(navController,apidata)
        }
    }


}