package com.example.metmuseum.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.metmuseum.model.Department

var listOfDepartment = listOf(
    Department(1, "American Decorative Arts"),
    Department(2, "Ancient Near Eastern Art"),
    Department(3, "Arms and Armor"),
    Department(4, "Arts of Africa, Oceania, and the Americas"),
    Department(5, "Asian Art"),
    Department(6, "The Cloisters"),
    Department(7, "The Costume Institute"),
    Department(8, "Drawings and Prints"),
    Department(9, "Egyptian Art"),
    Department(10, "European Paintings"),
    Department(11, "European Sculpture and Decorative Arts"),
    Department(12, "Greek and Roman Art"),
    Department(13, "Islamic Art"),
    Department(14, "The Robert Lehman Collection"),
    Department(15, "The Libraries"),
    Department(16, "Medieval Art"),
)

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun DepartmentcardPreview() {
    DepartmentGrid(onDepartmentClick = {})
}

@Composable
fun DepartmentGrid(
    onDepartmentClick: (department: Department) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ){
        items(listOfDepartment) {item ->
            DepartmentCard(
                department = item,
                onDepartmentClick = onDepartmentClick,
                modifier = Modifier
                    .padding(8.dp)
                    .heightIn(min = 68.dp)
                    //.fillMaxSize()
            )
        }
    }
}


@Composable
fun DepartmentCard(
    department: Department,
    onDepartmentClick: (department: Department) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface (
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            //.padding(4.dp)
            .fillMaxWidth()
            .clickable { onDepartmentClick(department) }
    ) {
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                //.width(255.dp)
        ) {
            Text(
                text = department.displayName,
                textAlign = TextAlign.Center,
                fontSize = 16.sp
            )
        }
    }
}