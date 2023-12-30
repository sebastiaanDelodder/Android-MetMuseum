package com.example.metmuseum.ui.artScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.metmuseum.model.Artpiece
import com.example.metmuseum.ui.components.ArtCard
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