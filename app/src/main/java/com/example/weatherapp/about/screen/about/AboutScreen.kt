package com.example.weatherapp.about.screen.about

import android.view.ViewGroup
import android.webkit.WebView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.viewinterop.AndroidView
import com.example.weatherapp.R
import com.example.weatherapp.common.util.Constants.SITE_URL

@Composable
fun AboutScreen(
    paddingValues: PaddingValues
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding()
            )
            .padding(dimensionResource(id = R.dimen.smallPadding)),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(R.string.createdBy),
                fontWeight = FontWeight.Light,
                fontFamily = FontFamily.Serif,
                fontSize = MaterialTheme.typography.headlineSmall.fontSize

            )
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(R.string.name),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                fontSize = MaterialTheme.typography.headlineMedium.fontSize
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.smallPadding))
                    .clip(RoundedCornerShape(dimensionResource(id = R.dimen.smallPadding)))
                    .weight(10f),
                contentAlignment = Alignment.Center,
            ) {
                AndroidView(factory = {
                    WebView(it).apply {
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                        settings.javaScriptEnabled = true
                        settings.loadWithOverviewMode = true
                        settings.setSupportZoom(true)
                    }
                }, update = {
                    it.loadUrl(SITE_URL)
                },
                )
            }
        }

    }
}