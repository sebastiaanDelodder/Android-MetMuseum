package com.example.metmuseum.ui.artScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    var search by remember { mutableStateOf("") }
    Column {
        Searchbar(
            search = search,
            onValueChange = { search = it },
            modifier = modifier
                .padding(16.dp)
        )
        Text(text = search)
        Spacer(modifier = Modifier.height(16.dp))
        ArtScreenColumn(modifier = modifier)
    }
}