package com.example.metmuseum.ui.artScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.metmuseum.model.Department
import com.example.metmuseum.ui.components.DepartmentGrid

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE, heightDp = 650)
@Composable
fun DepartmentPreview() {
    DepartmentScreen(onDepartmentClick = {})
}
@Composable
fun DepartmentScreen(
    onDepartmentClick: (department: Department) -> Unit,
    modifier : Modifier = Modifier
) {
    Column (
        modifier = modifier
            .fillMaxSize()
    ){
        Text(text = "Departments")
        Spacer(modifier = Modifier.height(16.dp))
        DepartmentGrid(
            onDepartmentClick = onDepartmentClick,
        )
    }
}