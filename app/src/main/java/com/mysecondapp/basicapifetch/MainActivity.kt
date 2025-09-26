package com.mysecondapp.basicapifetch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Modifier
import com.mysecondapp.basicapifetch.Presentation.ViewModel.MyViewModel
import com.mysecondapp.basicapifetch.Presentation.routes.AppNavigation
import com.mysecondapp.basicapifetch.ui.theme.BasicAPIFetchTheme
import kotlin.getValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataModel by viewModels<MyViewModel>()
        enableEdgeToEdge()
        setContent {
            BasicAPIFetchTheme {
                Box(
                    modifier = Modifier
                ){
                    AppNavigation(dataModel)
                }
            }
        }
    }
}

