package com.example.metmuseum.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.metmuseum.R

/**
 * A composable function representing the top bar of the application.
 *
 * @param modifier The modifier to be applied to the composable.
 * @param title The resource ID of the title to be displayed in the top bar. If null, the title won't be displayed.
 *
 * @see Composable
 * @see ExperimentalMaterial3Api
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier : Modifier = Modifier,
    @StringRes title: Int?,
) {
    title?.let {
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary
            ),
            title = {
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = null,
                        modifier = Modifier
                            .size(dimensionResource(id = R.dimen.img_size_small))
                    )
                    Text(
                        text = stringResource(it),
                        modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_medium)),
                    )
                }
            },
            modifier = modifier
        )
    }
}