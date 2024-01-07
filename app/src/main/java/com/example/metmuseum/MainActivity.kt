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

/**
 * The main activity of the Met Museum app, responsible for setting up the user interface and navigation.
 *
 * This activity utilizes Jetpack Compose for building the UI and ExperimentalMaterial3WindowSizeClassApi
 * for calculating the window size class.
 *
 * @constructor Creates a new instance of MainActivity.
 */
class MainActivity : ComponentActivity() {

    /**
     * Overrides the `onCreate` method to initialize the activity's UI and content.
     *
     * @param savedInstanceState The saved instance state, if any.
     */
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