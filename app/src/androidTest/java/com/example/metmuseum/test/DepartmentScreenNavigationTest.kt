package com.example.metmuseum.test

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.metmuseum.R
import com.example.metmuseum.ui.MetMuseumApp
import com.example.metmuseum.ui.util.NavigationType
import org.junit.Before
import org.junit.Rule
import org.junit.Test


/**
 * UI test class for navigation within the Met Museum app's Department screen.
 *
 * This test class uses the AndroidComposeRule for UI testing with Jetpack Compose
 * and checks the navigation behavior within the app.
 *
 * Note: To access an empty activity, the code uses [ComponentActivity] instead of MainActivity.
 *
 * @see Rule
 * @see createAndroidComposeRule
 * @see Before
 * @see Test
 */
class DepartmentScreenNavigationTest {
    /**
     * AndroidComposeRule for UI testing with Jetpack Compose.
     */
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    /**
     * TestNavHostController for simulating navigation events and verifying destinations.
     */
    private lateinit var navController: TestNavHostController


    /**
     * Set up the Met Museum NavHost with the specified [NavigationType] before each test.
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
     * Test case: Navigate to the Art Overview screen and verify the presence of a specific item.
     */
    @Test
    fun metMuseumNavHost_navigateToArtOverviewScreen() {
        navigateToArtScreen()

        //DATA LOAD
        loadWait()

        composeTestRule
            .onNodeWithText(
                text= "Greek",
                substring = true,
            ).assertIsDisplayed()
    }

    /**
     * Test case: Navigate to the Art Overview screen, go back to the Departments screen,
     * and verify the presence of a specific item.
     */
    @Test
    fun metMuseumNavHost_navigateToArtOverviewScreenAndBackToDepartments() {
        navigateToArtScreen()

        //DATA LOAD
        loadWait()

        composeTestRule
            .onNodeWithText(
                text= "Greek",
                substring = true,
            ).performClick()

        loadWait()

        composeTestRule
            .onNodeWithContentDescription("Back").performClick()

        loadWait()
        composeTestRule
            .onNodeWithText(
                text= "Greek",
                substring = true,
            ).assertIsDisplayed()
    }

    /**
     * Navigate to the Art screen using a specific action.
     */
    private fun navigateToArtScreen() {
        composeTestRule
            .onNodeWithStringId(R.string.art).performClick()
    }

    /**
     * Navigate to the Art screen using a specific action.
     */
    private fun loadWait(){
        Thread.sleep(2000)
        composeTestRule.waitForIdle()
        Thread.sleep(2000)
    }
}