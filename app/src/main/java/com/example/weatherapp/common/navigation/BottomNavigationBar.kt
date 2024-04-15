package com.example.weatherapp.common.navigation

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivityResultRegistryOwner
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.weatherapp.R
import com.example.weatherapp.common.navigation.routes.BottomNavItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    index: Int
) {
    val navigationBarItems = listOf(
        BottomNavItem.Home,
        BottomNavItem.WeeklyWeather,
        BottomNavItem.AirPollution,
        BottomNavItem.About
    )

    var selectedIndex by rememberSaveable {
        mutableIntStateOf(index)
    }
    var firstPressed by remember { mutableStateOf(false) }

    val context = LocalContext.current

    var color by remember {
        mutableStateOf(Color.LightGray)
    }


    NavigationBar(
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.mediumPadding))
            .height(dimensionResource(id = R.dimen.largeHeight))
            .clip(RoundedCornerShape(dimensionResource(id = R.dimen.largeCornerRadius))),
        containerColor = MaterialTheme.colorScheme.primary
    ) {

        navigationBarItems.forEachIndexed { index, item ->

                color  = if (selectedIndex == index) Color.Cyan
                else  Color.LightGray

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null
                    ) {
                        selectedIndex = index
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true

                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(dimensionResource(id = R.dimen.iconSize)),
                    painter = painterResource(id = item.icon),
                    contentDescription = item.title,
                    tint = color
                )

            }
            BackHandler {

                if (firstPressed) {
                    val activity = (context as? Activity)
                    activity?.finish()
                } else {
                    selectedIndex = 0
                    firstPressed = true
                }
            }
        }


    }
}
