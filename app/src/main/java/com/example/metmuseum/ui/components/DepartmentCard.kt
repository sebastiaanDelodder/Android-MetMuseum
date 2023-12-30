package com.example.metmuseum.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true, widthDp = 300, heightDp = 100, backgroundColor = 845)
@Composable
fun DepartmentcardPreview() {
    DepartmentCard()
}
@Composable
fun DepartmentCard(
    modifier: Modifier = Modifier
) {
    Surface (
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .padding(8.dp)
            //.heightIn(min = 68.dp)
            .fillMaxSize()
    ) {
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .width(255.dp)

        ) {
            Text(
                text = "Test",
                textAlign = TextAlign.Center,
            )
        }
    }
}