package com.example.booster.data

import com.example.booster.navigation.Screen

/**
 *
 * Random constants needed.
 *
 **/


object InvestorTypeLabelList {
    val list = listOf("Defensive", "Conservative", "Balanced", "Growth", "Aggressive Growth")
}

object NavItems {
    val navItems: NavItem = NavItem(
        Screen.TypeScreen.title,
        InvestorTypeLabelList.list,
        Screen.TypeScreen
    )
}
data class NavItem(val title: String, val links: List<String>, val screen: Screen)

object HomeScreenData {
    const val headerText = "Risk Profiler"
    const val subHeaderText = "What type of investor are you?"
    const val secondParagraph = "Understanding risk is about determining your capacity for risk and uncertainty, as well as your attitude towards risk."
    const val thirdParagraph = "To find out what type of investor you are, open the menu and read through each of the types."
    const val fourthParagraph =
                "Remember, this is a guide only and may not accurately reflect your particular circumstances. " +
                "Before making your choice you should refer to the relevant Booster KiwiSaver Scheme Product Disclosure " +
                "Statement and talk with a financial adviser to help you plan for your future. A disclosure statement is " +
                "available from them, on request and free of charge."
}