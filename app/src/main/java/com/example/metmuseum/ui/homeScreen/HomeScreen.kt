package com.example.metmuseum.ui.homeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.metmuseum.R

/**
 * Composable function representing the home screen of the application.
 *
 * This composable displays various content such as an image, text paragraphs, and spacing within a LazyColumn.
 *
 * @param modifier Modifier for styling and layout customization.
 */
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(
                vertical = dimensionResource(id = R.dimen.padding_large),
                horizontal = dimensionResource(id = R.dimen.padding_xlarge)
            )
    ){
        item {
            Spacer(modifier = Modifier.height(
                dimensionResource(id = R.dimen.padding_xlarge)
            ))
            Image(
                painter = painterResource(id = R.drawable.logohome),
                contentDescription = "Logo of the Met Museum",
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.img_size_medium))
            )
            Spacer(modifier = Modifier.height(
                dimensionResource(id = R.dimen.padding_xxxlarge)
            ))
            Text(
                text = stringResource(id = R.string.home_par1),
            )
            Spacer(modifier = Modifier.height(
                dimensionResource(id = R.dimen.padding_large)
            ))
            Text(
                text = stringResource(id = R.string.home_par2),
            )
            Spacer(modifier = Modifier.height(
                dimensionResource(id = R.dimen.padding_large)
            ))
            Text(
                text = stringResource(id = R.string.home_par3),
            )
        }
    }
}