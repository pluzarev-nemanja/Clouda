package com.example.weatherapp.common.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R

@Composable
fun ThemeSwitcher(
    onThemeSwitch: () -> Unit,
    isInDarkTheme: Boolean
) {
    val rotationAngle by animateFloatAsState(
        targetValue = if (isInDarkTheme) 360f else 0f,
        label = stringResource(R.string.rotationAngle)
    )

    Box(
        modifier = Modifier
            .padding(end = dimensionResource(id = R.dimen.mediumPadding))
            .clip(CircleShape)
            .background(color = MaterialTheme.colorScheme.inversePrimary),
        contentAlignment = Alignment.Center
    ) {

        IconButton(onClick = onThemeSwitch) {
            Icon(

                painter = painterResource(id = if (isInDarkTheme) R.drawable.ic_sun else R.drawable.ic_moon),
                contentDescription = stringResource(R.string.themeIcon),
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .size(100.dp)
                    .graphicsLayer(rotationZ = rotationAngle),
            )
        }
    }
}