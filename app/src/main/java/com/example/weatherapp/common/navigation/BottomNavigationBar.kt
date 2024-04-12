package com.example.weatherapp.common.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.weatherapp.R
import com.example.weatherapp.common.navigation.routes.BottomNavItem

@Composable
fun BottomNavigationBar(
    navController: NavHostController
) {
    val navigationBarItems = listOf(
        BottomNavItem.Home,
        BottomNavItem.WeeklyWeather,
        BottomNavItem.AirPollution,
        BottomNavItem.About
    )

    var selectedIndex by rememberSaveable {
        mutableIntStateOf(0)
    }


    NavigationBar(
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.mediumPadding))
            .height(dimensionResource(id = R.dimen.largeHeight))
            .clip(RoundedCornerShape(dimensionResource(id = R.dimen.largeCornerRadius))),
        containerColor = MaterialTheme.colorScheme.primary
    ) {

        navigationBarItems.forEachIndexed { index, item ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null
                    ) {
                        selectedIndex = index
                        navController.navigate(item.route)
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(dimensionResource(id = R.dimen.iconSize)),
                    painter = painterResource(id = item.icon),
                    contentDescription = item.title,
                    tint = if (selectedIndex == index) MaterialTheme.colorScheme.inversePrimary
                    else Color.LightGray
                )
            }
        }

    }
}
