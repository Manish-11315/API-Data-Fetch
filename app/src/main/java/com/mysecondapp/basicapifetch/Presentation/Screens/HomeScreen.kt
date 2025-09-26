package com.mysecondapp.basicapifetch.Presentation.Screens

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mysecondapp.basicapifetch.R
import com.mysecondapp.basicapifetch.Presentation.ViewModel.MyViewModel
import com.mysecondapp.basicapifetch.Presentation.routes.Descriptionscreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: MyViewModel) {
    LaunchedEffect(key1 = Unit) {
        viewModel.loadCoffeeData()
    }

    val state = viewModel.currentState.collectAsState()
    val darkTheme = isSystemInDarkTheme()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = if (darkTheme) colorResource(id = R.color.teal_700) else colorResource(id = R.color.teal_200),
                    titleContentColor = if (darkTheme) colorResource(id = R.color.white) else colorResource(id = R.color.black),
                ),
                title = {
                    Column {
                        Text(
                            text = "Home Screen",
                            style = MaterialTheme.typography.headlineMedium
                        )
                        Text(
                            text = "Basic API Data Fetch",
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }
                },
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if (state.value.is_loading == true) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else if (state.value.is_error.isNullOrEmpty().not()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = state.value.is_error.toString())
                }
            } else {
                val coffeeHotList = state.value.coffeeList

                if (coffeeHotList.isNullOrEmpty()) {
                    Text("No Data Found")
                } else {
                    Column {
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                            items(coffeeHotList) { coffeeItem ->
                                ElevatedCard(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight()
                                        .padding(12.dp),
                                    elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                                    onClick = {
                                        navController.navigate(
                                            Descriptionscreen(
                                                description = coffeeItem.description,
                                                id = coffeeItem.id,
                                                image = coffeeItem.image,
                                                ingredients = coffeeItem.ingredients,
                                                title = coffeeItem.title
                                            )
                                        )
                                    }
                                ) {
                                    Column(modifier = Modifier.padding(8.dp)) {
                                        coffeeItem.title?.let { text ->
                                            Text(
                                                text = text,
                                                style = MaterialTheme.typography.titleMedium
                                            )
                                        }
                                        coffeeItem.description?.let { text ->
                                            Text(
                                                text = text,
                                                style = MaterialTheme.typography.bodySmall
                                            )
                                        }
                                        Text(
                                            text = "Ingredients: ${coffeeItem.ingredients?.joinToString()}",
                                            style = MaterialTheme.typography.bodySmall
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}