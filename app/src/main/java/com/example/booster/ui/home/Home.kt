package com.example.booster.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.booster.R
import com.example.booster.data.HomeScreenData
import com.example.booster.ui.theme.BoosterSpacings

@Composable
fun Home() {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.primaryVariant)
            .fillMaxHeight()
            .fillMaxWidth()
    ) {

        Text(
            text = HomeScreenData.headerText,
            color = colorResource(id = R.color.white),
            style = MaterialTheme.typography.h4,
            modifier = Modifier
                .padding(
                    start = BoosterSpacings.spaceMedium,
                    top = BoosterSpacings.space28,
                    end = BoosterSpacings.spaceMedium
                )
        )

        Text(
            text = HomeScreenData.subHeaderText,
            color = colorResource(id = R.color.white),
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(horizontal = BoosterSpacings.spaceMedium)
        )

        Divider(
            color = colorResource(id = R.color.white),
            thickness = 2.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(BoosterSpacings.spaceMedium)
        )

        Text(
            text = HomeScreenData.secondParagraph,
            color = colorResource(id = R.color.white),
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(BoosterSpacings.spaceMedium)
        )
        Text(
            text = HomeScreenData.thirdParagraph,
            color = colorResource(id = R.color.white),
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .padding(
                    start = BoosterSpacings.spaceMedium,
                    top = BoosterSpacings.spaceMedium,
                    end = BoosterSpacings.spaceMedium,
                    bottom = BoosterSpacings.space28
                )
        )

        Divider(
            color = Color.White,
            thickness = BoosterSpacings.spaceXxxSmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    BoosterSpacings.spaceMedium
                )
        )

        Text(
            text = HomeScreenData.fourthParagraph,
            color = colorResource(id = R.color.white),
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(BoosterSpacings.spaceMedium)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun HomePreview() {
    Home()
}