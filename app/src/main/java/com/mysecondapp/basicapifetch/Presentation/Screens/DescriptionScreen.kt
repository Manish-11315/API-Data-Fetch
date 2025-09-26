package com.mysecondapp.basicapifetch.Presentation.Screens

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
// import androidx.compose.material.icons.filled.ArrowBack // This was duplicated, removed
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import com.mysecondapp.basicapifetch.R
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
// import androidx.navigation.NavHostController // Not used, can be removed
import coil3.compose.AsyncImage
import com.mysecondapp.basicapifetch.Data.models.ApiRes_Coffee_hotItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DescriptionScreen(navController: NavController,coffeItems: ApiRes_Coffee_hotItem,) {
    val DarkTheme = isSystemInDarkTheme()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = // Corrected lambda placement for topBar
         {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically){
                        IconButton(
                            onClick = {
                                navController.popBackStack()
                            },
                            modifier = Modifier.size(56.dp)
                        ){
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                modifier = Modifier.size(24.dp),
                                tint = if (DarkTheme) colorResource(id = R.color.white) else colorResource(
                                    id = R.color.black
                                )
                            )
                        }
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(text = "Description Screen")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor= if (DarkTheme) {
                        colorResource(id = R.color.teal_700)
                    } else {
                        colorResource(id = R.color.teal_200)
                    },
                    titleContentColor = if(DarkTheme){
                        colorResource(id = R.color.white)
                    } else {
                        colorResource(id = R.color.black)
                    }
                )
            )
        }

    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top
            ) {
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp, start = 12.dp, end = 12.dp)
                        .wrapContentHeight(),
                    shape = RoundedCornerShape(topEnd = 12.dp, topStart = 12.dp),
                    elevation = CardDefaults.cardElevation(20.dp),
                ) {
                    Text(
                        modifier = Modifier.padding(25.dp),
                        text = coffeItems.title.toString()
                    )

                }
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 12.dp, end = 12.dp, top = 0.dp)
                        .wrapContentHeight(),
                    shape = RoundedCornerShape(bottomEnd = 12.dp, bottomStart = 12.dp),
                    elevation = CardDefaults.cardElevation(20.dp)
                ) {
                    AsyncImage(
                        model = coffeItems.image,
                        contentDescription = "image",
                        modifier = Modifier.fillMaxWidth().height(200.dp), // Added fillMaxWidth
                        contentScale = ContentScale.Crop,
                    )
                }
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                        .wrapContentHeight(),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(20.dp)
                ) {
                    Text(
                        modifier = Modifier.padding(25.dp),
                        text = coffeItems.description.toString()
                    )
                }
            }
        }
    }
}
