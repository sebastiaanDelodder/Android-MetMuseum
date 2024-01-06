package com.example.metmuseum.test

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.metmuseum.R
import com.example.metmuseum.ui.MetMuseumApp
import com.example.metmuseum.ui.navigation.Destinations
import com.example.metmuseum.ui.util.NavigationType
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.internal.wait
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeScreenNavigationTest {
    /**
     * Note: To access to an empty activity, the code uses ComponentActivity instead of
     * MainActivity.
     */
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupMetMuseumNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            MetMuseumApp(navController = navController, navigationType = NavigationType.BOTTOM_NAVIGATION)
        }
    }

    @Test
    fun metMuseumNavHost_verifyStartDestination() {
        navController.assertCurrentRouteName(Destinations.Home.name)
    }

    @Test
    fun metMuseumNavHost_navigateToArtScreen() {
        navigateToDepartmentsScreen()
        navController.assertCurrentRouteName(Destinations.Art.name)
    }

    @Test
    fun metMuseumNavHost_navigateToDepartmentsScreenAndBackToHome() {
        navigateToDepartmentsScreen()

        composeTestRule
            .onNodeWithStringId(R.string.home).performClick()
        navController.assertCurrentRouteName(Destinations.Home.name)
    }

    @Test
    fun metMuseumNavHost_contentsOnScreen_arePresent(){

        composeTestRule
            .onNodeWithContentDescription("Logo of the Met Museum")
            .assertIsDisplayed()
    }

    private fun navigateToDepartmentsScreen() {
        composeTestRule
            .onNodeWithStringId(R.string.art).performClick()
    }
}