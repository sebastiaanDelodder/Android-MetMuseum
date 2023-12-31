package com.example.metmuseum.ui.components

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
)

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun DepartmentcardPreview() {
    DepartmentGrid()
}

@Composable
fun DepartmentGrid(
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
    modifier: Modifier = Modifier
) {
    Surface (
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            //.padding(4.dp)
            .fillMaxWidth(),
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