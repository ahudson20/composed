package com.example.booster.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.booster.logic.InvestorTypeViewModel
import com.example.booster.logic.InvestorUiState.InvestorDetails
import com.example.booster.ui.form.Form
import com.example.booster.ui.home.Home
import com.example.booster.ui.type.TypeScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    uiState: InvestorDetails,
    viewModel: InvestorTypeViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        composable(
            route = Screen.HomeScreen.route
        ) {
            Home()
        }

        composable(
            route = Screen.TypeScreen.route + "/{id}"
        ) { navBackStack ->
            val screenName = navBackStack.arguments?.getString("id")
            val dataObject = uiState.fundDetail.first { it.name == screenName }
            TypeScreen(
                screenData = dataObject
            )
        }

        composable(
            route = Screen.FormScreen.route
        ) {
            Form(uiState = uiState, viewModel = viewModel)
        }

    }
}