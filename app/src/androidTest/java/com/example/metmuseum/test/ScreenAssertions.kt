package com.example.metmuseum.test

import androidx.navigation.NavController
import org.junit.Assert

/**
 * Asserts that the current route name in the [NavController] matches the expected route name.
 *
 * This extension function is useful for testing navigation within a NavController.
 *
 * @param expectedRouteName The expected route name to compare against the current route.
 *
 * @throws AssertionError If the current route name does not match the expected route name.
 *
 * @see NavController
 */
fun NavController.assertCurrentRouteName(expectedRouteName: String) {
    Assert.assertEquals(expectedRouteName, currentBackStackEntry?.destination?.route)
}