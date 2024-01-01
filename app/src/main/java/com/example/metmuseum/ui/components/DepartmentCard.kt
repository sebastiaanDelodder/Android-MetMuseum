package com.example.metmuseum.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.example.metmuseum.data.DepartmentSampler
import com.example.metmuseum.model.Department


@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun DepartmentcardPreview() {
    DepartmentGrid(onDepartmentClick = {}, departments = DepartmentSampler.getAll())
}

@Composable
fun DepartmentGrid(
    onDepartmentClick: (department: Department) -> Unit,
    departments: List<Department>,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .padding(vertical = 24.dp)
    ){
        items(departments) {item ->
            DepartmentCard(
                department = item,
                onDepartmentClick = onDepartmentClick,
                modifier = Modifier
                    .padding(8.dp)

                    //.height(68.dp)
                    .heightIn(min = 68.dp, max = 100.dp)
                    //.fillMaxSize()
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
            .clickable { onDepartmentClick(department) },
        color = MaterialTheme.colorScheme.secondaryContainer,
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
                fontSize = 16.sp,
                lineHeight = 16.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
            )
        }
    }
}