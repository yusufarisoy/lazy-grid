package com.yusufarisoy.lazygridapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.yusufarisoy.lazygridapp.ui.theme.LazyGridAppTheme
import com.yusufarisoy.lazygridapp.view.NavigationGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyGridAppTheme {
                val navController = rememberNavController()

                NavigationGraph(navController)
            }
        }
    }
}
