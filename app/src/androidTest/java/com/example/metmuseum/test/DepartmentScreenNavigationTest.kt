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

class DepartmentScreenNavigationTest {
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

    private fun navigateToArtScreen() {
        composeTestRule
            .onNodeWithStringId(R.string.art).performClick()
    }

    private fun loadWait(){
        Thread.sleep(2000)
        composeTestRule.waitForIdle()
        Thread.sleep(2000)
    }
}