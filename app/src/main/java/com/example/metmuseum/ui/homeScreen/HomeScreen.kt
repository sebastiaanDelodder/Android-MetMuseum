package com.example.metmuseum.ui.homeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
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

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(
                vertical = dimensionResource(id = R.dimen.padding_large),
                horizontal = dimensionResource(id = R.dimen.padding_large)
            )
    ){
        item {
            Spacer(modifier = Modifier.padding(
                dimensionResource(id = R.dimen.padding_large)
            ))
            Image(
                painter = painterResource(id = R.drawable.logohome),
                contentDescription = null,
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.img_size_medium))
            )
            Spacer(modifier = Modifier.padding(
                dimensionResource(id = R.dimen.padding_xlarge)
            ))
            Text(
                text = stringResource(id = R.string.home_par1),
            )
            Spacer(modifier = Modifier.padding(
                dimensionResource(id = R.dimen.padding_medium)
            ))
            Text(
                text = stringResource(id = R.string.home_par2),
            )
            Spacer(modifier = Modifier.padding(
                dimensionResource(id = R.dimen.padding_medium)
            ))
            Text(
                text = stringResource(id = R.string.home_par3),
            )
        }
    }
}