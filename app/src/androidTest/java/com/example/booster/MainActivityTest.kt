package com.example.booster

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.booster.logic.FundDetailsDTO
import com.example.booster.logic.FundMix
import com.example.booster.logic.InvestorUiState
import com.example.booster.logic.InvestorUiState.*
import com.example.booster.ui.theme.BoosterTheme
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    // createAndroidComposeRule<YourActivity>() if you need access to an activity

//    @Test
//    fun openFirstInvestorType() {
//        composeTestRule.setContent {
//            BoosterTheme {
//                MainScreen(
//                    getMockStateDataMainScreen()
//                )
//            }
//        }
//
//        composeTestRule.onNodeWithContentDescription("Navigation Menu").performClick()
//        composeTestRule.onNodeWithText("test-one").performClick()
//        composeTestRule.onNodeWithText("Test").assertIsDisplayed()
//    }
//
//    @Test
//    fun openSecondInvestorType() {
//        composeTestRule.setContent {
//            BoosterTheme {
//                MainScreen(
//                    getMockStateDataMainScreen()
//                )
//            }
//        }
//
//        composeTestRule.onNodeWithContentDescription("Navigation Menu").performClick()
//        composeTestRule.onNodeWithText("test-two").performClick()
//        composeTestRule.onNodeWithText("TestTwo").assertIsDisplayed()
//    }
//
//    @Test
//    fun openThirdInvestorType() {
//        composeTestRule.setContent {
//            BoosterTheme {
//                MainScreen(
//                    getMockStateDataMainScreen()
//                )
//            }
//        }
//
//        composeTestRule.onNodeWithContentDescription("Navigation Menu").performClick()
//        composeTestRule.onNodeWithText("test-three").performClick()
//        composeTestRule.onNodeWithText("TestThree").assertIsDisplayed()
//    }
//
//    private fun getMockStateDataMainScreen(): InvestorDetails {
//        return InvestorDetails(fundDetail = listOf(
//            FundDetailsDTO(
//                "name",
//                "fundName",
//                listOf("detail1", "detail2"),
//                listOf(FundMix("sectionTitle", 1, "assetType"))
//            ))
//        )
//    }
}