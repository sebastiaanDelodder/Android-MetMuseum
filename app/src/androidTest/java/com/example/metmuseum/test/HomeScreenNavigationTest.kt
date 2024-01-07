package com.example.metmuseum.test

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.metmuseum.R
import com.example.metmuseum.ui.MetMuseumApp
import com.example.metmuseum.ui.navigation.Destinations
import com.example.metmuseum.ui.util.NavigationType
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Instrumented UI test class for verifying navigation within the Met Museum app's home screen.
 *
 * This test class uses the AndroidComposeRule for creating Compose UI tests and TestNavHostController
 * for controlling navigation within the app.
 *
 * @constructor Creates an instance of [HomeScreenNavigationTest].
 *
 * @see Rule
 * @see createAndroidComposeRule
 * @see Before
 * @see Test
 */
class HomeScreenNavigationTest {

    /**
     * Note: To access to an empty activity, the code uses ComponentActivity instead of
     * MainActivity.
     */
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    /**
     * TestNavHostController for simulating navigation events and verifying destinations.
     */
    private lateinit var navController: TestNavHostController


    /**
     * Sets up the Met Museum NavHost with the specified navigation type.
     */
    @Before
    fun setupMetMuseumNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            MetMuseumApp(navController = navController, navigationType = NavigationType.BOTTOM_NAVIGATION)
        }
    }

    /**
     * Verifies that the start destination of the Met Museum NavHost is the Home screen.
     */
    @Test
    fun metMuseumNavHost_verifyStartDestination() {
        navController.assertCurrentRouteName(Destinations.Home.name)
    }

    /**
     * Navigates to the Art screen and verifies the current route.
     */
    @Test
    fun metMuseumNavHost_navigateToArtScreen() {
        navigateToDepartmentsScreen()
        navController.assertCurrentRouteName(Destinations.Art.name)
    }

    /**
     * Navigates to the Departments screen, clicks on the Home button, and verifies the current route.
     */
    @Test
    fun metMuseumNavHost_navigateToDepartmentsScreenAndBackToHome() {
        navigateToDepartmentsScreen()

        composeTestRule
            .onNodeWithStringId(R.string.home).performClick()
        navController.assertCurrentRouteName(Destinations.Home.name)
    }

    /**
     * Verifies the presence of the Met Museum logo on the screen.
     */
    @Test
    fun metMuseumNavHost_contentsOnScreen_arePresent(){

        composeTestRule
            .onNodeWithContentDescription("Logo of the Met Museum")
            .assertIsDisplayed()
    }

    /**
     * Navigates to the Departments screen by clicking on the Art button.
     */
    private fun navigateToDepartmentsScreen() {
        composeTestRule
            .onNodeWithStringId(R.string.art).performClick()
    }
}