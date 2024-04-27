package com.example.weatherapp.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import coil.compose.AsyncImage
import com.example.weatherapp.R


@Composable
fun ErrorScreen(
    message: String?,
    onRetryClick : () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            AsyncImage(
                model = R.drawable.ic_error,
                contentDescription = stringResource(R.string.errorIcon),
                modifier = Modifier.size(dimensionResource(id = R.dimen.LargeIconSize))
            )

            Text(text = message ?: stringResource(R.string.unknownError),
                fontFamily = FontFamily.Serif,
                fontSize = MaterialTheme.typography.titleSmall.fontSize,
                fontWeight = FontWeight.SemiBold
                )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.mediumPadding)))
            Button(onClick = onRetryClick) {
                Text(text = stringResource(R.string.refresh))

            }
        }
    }
}