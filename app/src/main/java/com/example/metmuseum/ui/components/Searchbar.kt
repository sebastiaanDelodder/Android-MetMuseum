package com.example.metmuseum.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.metmuseum.R


@Preview(showBackground = true, widthDp = 300)
@Composable
fun PrevSearch() {
    Searchbar(
        search = "",
        onValueChange = {},
        modifier = Modifier.padding(8.dp))
}

@Composable
fun Searchbar(
    search: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = search,
        onValueChange = onValueChange,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
            focusedTextColor = MaterialTheme.colorScheme.onTertiaryContainer,
        ),
        placeholder = {
            Text(
                stringResource(id = R.string.search)
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = dimensionResource(id = R.dimen.textfield_min)),
    )
}