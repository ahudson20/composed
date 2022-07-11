package com.example.booster.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.booster.R
import com.example.booster.data.NavItems
import com.example.booster.ui.theme.BoosterSpacings
import com.example.booster.ui.theme.BoosterTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun Drawer(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    navController: NavController
) {
    val navItemsList = listOf(NavItems.navItems)
    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.primary)
            .fillMaxHeight()
            .fillMaxWidth()
    ) {

        DrawerMenuTitle(modifier = Modifier.align(Alignment.CenterHorizontally))

        Divider(
            color = Color.White,
            thickness = BoosterSpacings.spaceXxxSmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(BoosterSpacings.space10)
        )

        DrawerSingleItem(scope = scope, scaffoldState = scaffoldState, navController = navController, stringResource(id = R.string.nav_drawer_home))

        navItemsList.forEach { navItem ->
            TypeBlock(
                testItem = navItem,
                scope = scope,
                scaffoldState = scaffoldState,
                navController = navController
            )
        }

        DrawerSingleItem(scope = scope, scaffoldState = scaffoldState, navController = navController, stringResource(id = R.string.nav_drawer_form))

    }
}

@Composable
fun DrawerMenuTitle(
    modifier: Modifier
) {
    Text(
        text = stringResource(id = R.string.nav_drawer_title),
        fontSize = 20.sp,
        color = Color.White,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(
                bottom = BoosterSpacings.spaceLarge,
                top = BoosterSpacings.spaceXLarge
            ).then(modifier)
    )
}

@Composable
fun DrawerSingleItem(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    navController: NavController,
    title: String
) {
    Row(
        modifier = Modifier.padding(
            start = BoosterSpacings.spaceXLarge,
            top = BoosterSpacings.space10,
            bottom = BoosterSpacings.space10,
            end =  BoosterSpacings.spaceXLarge
        )
    ) {
        when(title){
            "Home" -> HomeIcon()
            else -> InfoIcon()
        }

        Text(
            text = title.uppercase(),
            fontSize = 18.sp,
            color = Color.White,
            modifier = Modifier
                .padding(start = BoosterSpacings.spaceXSmall)
                .clickable {
                    navController.navigate(route = title.lowercase()) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                }
        )
    }
}

@Composable
fun HomeIcon() {
    Icon(
        Icons.Outlined.Home,
        "Home icon",
        tint = MaterialTheme.colors.secondary
    )
}

@Composable
fun InfoIcon() {
    Icon(
        Icons.Outlined.Info,
        "Info icon",
        tint = MaterialTheme.colors.secondary
    )
}


@Preview(showBackground = false)
@Composable
fun DrawerSingleItemPreview() {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val navController = rememberNavController()
    BoosterTheme {
        DrawerSingleItem(scope, scaffoldState, navController, "home")
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DrawerPreview() {
//    val scope = rememberCoroutineScope()
//    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
//    val navController = rememberNavController()
//    BoosterTheme {
//        Drawer(
//            scope = scope,
//            scaffoldState = scaffoldState,
//            navController = navController,
//            dataList = listOf(
//                FundDetailsDTO(
//                    "name",
//                    "fundName",
//                    listOf("one", "two", "three"),
//                    listOf(
//                        FundMix(
//                            "sectionTitle",
//                            1,
//                            "assetType"
//                        )
//                    )
//                )
//            )
//        )
//    }
//}