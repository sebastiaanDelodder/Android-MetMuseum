package com.example.metmuseum.ui.artScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.metmuseum.model.Department

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE, heightDp = 650)
@Composable
fun ArtScreenPrev() {
    ArtScreen()
}

@Composable
fun ArtScreen(
    modifier : Modifier = Modifier
) {
    var selectedDepartment by remember { mutableStateOf<Department?>(null) }

    if (selectedDepartment == null){
        DepartmentScreen(onDepartmentClick = { selectedDepartment = it })
    } else {
        ArtOverview(
            department = selectedDepartment!!,
            onBack = { selectedDepartment = null },
        )
    }
}