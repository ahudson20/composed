package com.example.booster.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.booster.data.NavItem
import com.example.booster.ui.theme.BoosterSpacings
import com.example.booster.ui.theme.BoosterTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TypeBlock(
    testItem: NavItem,
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(MaterialTheme.colors.primary)
            .padding(start = BoosterSpacings.spaceLarge)
    ){
        Spacer(modifier = Modifier.width(BoosterSpacings.spaceXSmall))
        Row(
            modifier = Modifier
                .padding(BoosterSpacings.spaceXSmall)
        ) {
            Icon(
                Icons.Outlined.Info,
                "",
                tint = MaterialTheme.colors.secondary
            )

            Text(
                text = testItem.title.uppercase(),
                fontSize = 18.sp,
                color = Color.White,
                modifier = Modifier
                    .padding(start = BoosterSpacings.spaceXSmall)
            )
        }
        Spacer(modifier = Modifier.width(BoosterSpacings.spaceMedium))
        Column(
            modifier = Modifier.padding(start = BoosterSpacings.space40)
        ) {
            testItem.links.forEach { item ->
                Spacer(modifier = Modifier.width(BoosterSpacings.space28))
                Text(
                    text = item,
                    fontSize = 18.sp,
                    color = Color.White,
                    modifier = Modifier
                        .padding(
                            top = BoosterSpacings.space5,
                            bottom = BoosterSpacings.space5
                        )
                        .clickable {
                            navController.navigate(testItem.screen.route + "/" + item) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                navController.graph.startDestinationRoute?.let { route ->
                                    popUpTo(route) {
                                        saveState = true
                                    }
                                }

                                /**
                                 * Don't want these!
                                 * Because we are technically navigating to the same route everytime,
                                 * we want multiple copies of the same destination
                                 * and we also don't want state restored.
                                 * I've disabled the back press anyway.
                                 * */
                                //launchSingleTop = true
                                //restoreState = true

                            }
                            // Close drawer
                            scope.launch {
                                scaffoldState.drawerState.close()
                            }
                        }
                )
                Spacer(modifier = Modifier.width(BoosterSpacings.space28))
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun NewDrawerItemPreview() {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val navController = rememberNavController()
    BoosterTheme {
        TypeBlock(
            testItem = NavItem(
                "YELLOW",
                listOf("aaa AAA", "bbb BBB", "cccc CCC"),
                Screen.TypeScreen
            ),
            scope = scope,
            scaffoldState = scaffoldState,
            navController = navController
        )
    }
}