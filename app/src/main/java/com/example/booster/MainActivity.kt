package com.example.booster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.DrawerValue
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.rememberNavController
import com.example.booster.logic.InvestorTypeViewModel
import com.example.booster.logic.InvestorUiState.InvestorDetails
import com.example.booster.logic.InvestorUiState.Loading
import com.example.booster.navigation.Drawer
import com.example.booster.navigation.SetupNavGraph
import com.example.booster.navigation.TopBar
import com.example.booster.ui.home.LoadingScreen
import com.example.booster.ui.theme.BoosterTheme

class MainActivity : ComponentActivity() {

    private val viewModel: InvestorTypeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BoosterTheme {
                when (val uiState = viewModel.uiState.collectAsState().value) {
                    is Loading -> LoadingScreen()
                    is InvestorDetails ->
                        MainScreen(uiState = uiState, viewModel = viewModel) //, viewModel = viewModel
                }
            }
        }
    }

    override fun onBackPressed() {
        // prevent returning to button screen
        return
    }
}

@Composable
fun MainScreen(
    uiState: InvestorDetails,
    viewModel: InvestorTypeViewModel
) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val coroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar(scope = coroutineScope, scaffoldState = scaffoldState)
        },
        drawerContent = {
            Drawer(
                scope = coroutineScope,
                scaffoldState = scaffoldState,
                navController = navController
            )
        }
    ) {
        SetupNavGraph(navController = navController, uiState = uiState, viewModel = viewModel)
    }
}

// TODO: pass viewModel into preview.
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    BoosterTheme {
//        MainScreen(
//            InvestorDetails(
//                fundDetail =
//                listOf(
//                    FundDetailsDTO(
//                        "name",
//                        "fundName",
//                        listOf("detail1", "detail2"),
//                        listOf(FundMix("sectionTitle", 1, "assetType"))
//                    )
//                )
//            )
//        )
//    }
//}