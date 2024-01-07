package com.example.metmuseum.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.metmuseum.R
import com.example.metmuseum.data.DepartmentSampler
import com.example.metmuseum.model.Department


/**
 * Composable function for previewing the [DepartmentGrid] in a UI preview.
 *
 * @see Preview
 * @see Composable
 */
@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun DepartmentcardPreview() {
    DepartmentGrid(onDepartmentClick = {}, departments = DepartmentSampler.getAll())
}


/**
 * Composable function for displaying a grid of [DepartmentCard] items.
 *
 * @param onDepartmentClick Callback function invoked when a department card is clicked.
 * @param departments List of departments to be displayed in the grid.
 * @param modifier Modifier for customizing the layout.
 *
 * @see Composable
 */
@Composable
fun DepartmentGrid(
    onDepartmentClick: (department: Department) -> Unit,
    departments: List<Department>,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = dimensionResource(id = R.dimen.padding_small)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacing_medium)),
        modifier = modifier
            .padding(
                horizontal = dimensionResource(id = R.dimen.padding_medium),
            )
    ){
        item (
            span = { GridItemSpan(2) }
        ){
            Text(
                text = stringResource(id = R.string.departments),
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = dimensionResource(id = R.dimen.padding_xlarge)
                    ),
            )
        }
        items(departments) {item ->
            DepartmentCard(
                department = item,
                onDepartmentClick = onDepartmentClick,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_medium))
                    .heightIn(
                        min = dimensionResource(id = R.dimen.card_size_min),
                        max = dimensionResource(id = R.dimen.card_size_max)
                    )
            )
        }
    }
}

/**
 * Composable function for displaying a single department card.
 *
 * @param department The department to be displayed.
 * @param onDepartmentClick Callback function invoked when the department card is clicked.
 * @param modifier Modifier for customizing the layout.
 *
 * @see Composable
 */
@Composable
fun DepartmentCard(
    department: Department,
    onDepartmentClick: (department: Department) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface (
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .fillMaxWidth()
            .clickable { onDepartmentClick(department) },
        color = MaterialTheme.colorScheme.secondaryContainer,
    ) {
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
        ) {
            Text(
                text = department.displayName,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                lineHeight = 16.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
    }
}