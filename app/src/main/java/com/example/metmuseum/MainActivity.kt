package com.example.metmuseum

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.Modifier
import com.example.metmuseum.ui.MetMuseumApp
import com.example.metmuseum.ui.theme.MetMuseumTheme
import com.example.metmuseum.ui.util.NavigationType

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MetMuseumTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val windowSize = calculateWindowSizeClass(this)

                    when (windowSize.widthSizeClass) {
                        WindowWidthSizeClass.Compact -> {
                            MetMuseumApp(NavigationType.BOTTOM_NAVIGATION)
                        }
                        WindowWidthSizeClass.Medium -> {
                            MetMuseumApp(NavigationType.NAVIGATION_RAIL)
                        }
                        WindowWidthSizeClass.Expanded -> {
                            MetMuseumApp(NavigationType.PERMANENT_NAVIGATION_DRAWER)
                        }
                        else -> {
                            MetMuseumApp(NavigationType.BOTTOM_NAVIGATION)
                        }
                    }
                }
            }
        }
    }
}