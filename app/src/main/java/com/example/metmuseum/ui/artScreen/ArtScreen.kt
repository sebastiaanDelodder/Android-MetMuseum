package com.example.metmuseum.ui.artScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.metmuseum.ui.components.ArtScreenColumn
import com.example.metmuseum.ui.components.DepartmentGrid
import com.example.metmuseum.ui.components.Searchbar

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE, heightDp = 650)
@Composable
fun ArtScreenPrev() {
    ArtScreen()
}

@Composable
fun ArtScreen(
    modifier : Modifier = Modifier
) {
    Column {
        Searchbar(modifier = modifier)
        Spacer(modifier = Modifier.height(16.dp))
        ArtScreenColumn(modifier = modifier)
        Spacer(modifier = Modifier.height(36.dp))
        DepartmentGrid()
    }
}