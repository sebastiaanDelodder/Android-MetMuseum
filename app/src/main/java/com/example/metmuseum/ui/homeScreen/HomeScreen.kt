package com.example.metmuseum.ui.homeScreen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    Text(
        modifier = Modifier.padding(8.dp),
        text =
        """
                    
        Welcome to The Metropolitan Museum of Art's mobile app—an immersive gateway to the world of art at your fingertips. Unleash the power of discovery as you embark on a journey through millennia of creativity and culture. Our app is your personalized guide to navigating the vast treasures housed within the museum's hallowed halls.

        Explore the app's user-friendly interface designed to effortlessly locate your favorite artworks or unearth hidden gems. Whether you're a seasoned art enthusiast or a curious newcomer, our search features ensure a seamless experience.

        Immerse yourself in the art world like never before. From ancient artifacts to contemporary masterpieces, The Met's mobile app is your key to unlocking the beauty, history, and inspiration that lie within the museum's unparalleled collection. Start your art adventure today!
        """.trimIndent(),
    )
}