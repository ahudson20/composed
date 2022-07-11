package com.example.booster.ui.type

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.booster.logic.FundDetailsDTO
import com.example.booster.logic.FundMix
import com.example.booster.ui.theme.*
import hu.ma.charts.legend.data.LegendEntry
import hu.ma.charts.legend.data.LegendPosition
import hu.ma.charts.pie.data.PieChartEntry
import me.bytebeats.views.charts.pie.PieChart
import me.bytebeats.views.charts.pie.PieChartData
import me.bytebeats.views.charts.pie.render.SimpleSliceDrawer
import kotlin.math.roundToInt

@Composable
fun TypeScreen(
    screenData: FundDetailsDTO
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(BoosterSpacings.spaceMedium),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            Text(
                text = "##This is a placeholder##",
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.h5
            )
        }
        item {
            Text(
                text = "I haven't designed this screen yet",
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.h6
            )
        }
        item {
            Text(
                text = "Need to map data to chart",
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.h6
            )
        }
        item {
            Divider(
                color = MaterialTheme.colors.primary,
                thickness = 2.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(BoosterSpacings.spaceMedium)
            )
        }

        item {
            Text(
                text = "Name: " + screenData.name!!,
                color = MaterialTheme.colors.primary
            )
            Text(
                text = "Fund name: " + screenData.fundName!!,
                color = MaterialTheme.colors.primary
            )
            Text(
                text = "Fund Details:",
                color = MaterialTheme.colors.primary,
                style = MaterialTheme.typography.h6
            )
        }
        items(items = screenData.fundDetails!!, itemContent =  { detail->
            Text(
                text = "--> $detail",
                color = MaterialTheme.colors.primary
            )
        })

        item {
            Spacer(modifier = Modifier.height(BoosterSpacings.spaceLarge))
            PieStyledScreen()
        }
//        item {
//            PieChartPreview(generateSlices(screenData.fundInvestmentMix))
//        }
    }
}

@Composable
fun ChartContainer(
    modifier: Modifier = Modifier,
    title: String,
    chartOffset: Dp = 12.dp,
    content: @Composable () -> Unit,
) {
    Column(modifier = modifier) {
        Text(title, style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.requiredSize(chartOffset))
        content()
    }
}

@Composable
internal fun buildValuePercentString(item: LegendEntry) = buildAnnotatedString {
    item.value?.let { value ->
        withStyle(
            style = MaterialTheme.typography.body2.toSpanStyle()
                .copy(color = MaterialTheme.colors.primary)
        ) {
            append(value.toInt().toString())
        }
        append(" ")
    }

    withStyle(
        style = MaterialTheme.typography.caption.toSpanStyle()
            .copy(color = MaterialTheme.colors.secondary)
    ) {
        val percentString = item.percent.roundToInt().toString()
        append("($percentString %)")
    }
}

val Categories = listOf(
    "Teams",
    "Locations",
    "Devices",
    "People",
    "Laptops"
)

val SimpleColors = listOf(
    Purple700,
    Purple500,
    boosterPrimary,
    boosterPrimaryVariant,
    boosterSecondary
)


internal val PieSampleData =
    hu.ma.charts.pie.data.PieChartData(
        entries = listOf(430f, 240f, 140f, 60f, 50f).mapIndexed { idx, value ->
            PieChartEntry(
                value = value,
                label = AnnotatedString(Categories[idx])
            )
        },
        colors = SimpleColors,
        legendPosition = LegendPosition.End,
        legendShape = CircleShape,
    )

@Composable
fun PieStyledScreen() {
    ChartContainer(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = BoosterSpacings.spaceMedium)
            .border(BorderStroke(1.dp, Color.LightGray), shape = RoundedCornerShape(16.dp))
            .padding(BoosterSpacings.spaceMedium)
            .animateContentSize(),
        title = "Pie Chart"
    ) {
        hu.ma.charts.pie.PieChart(
            data = PieSampleData,
            legend = { entries ->
                CustomVerticalLegend(entries = entries)
            }
        )
    }
}

@Composable
internal fun RowScope.CustomVerticalLegend(entries: List<LegendEntry>) {
    Column(
        modifier = Modifier.Companion.weight(1f),
    ) {
        entries.forEachIndexed { idx, item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 14.dp)
            ) {
                Box(
                    Modifier
                        .requiredSize(item.shape.size)
                        .background(item.shape.color, item.shape.shape)
                )

                Spacer(modifier = Modifier.requiredSize(8.dp))

                Text(
                    text = item.text,
                    style = MaterialTheme.typography.caption
                )
                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = buildValuePercentString(item),
                    style = MaterialTheme.typography.caption,
                )
            }

            if (idx != entries.lastIndex)
                Divider()
        }
    }
}


@Composable
fun PieChartPreview(sliceList: List<PieChartData.Slice>) = PieChart(
    pieChartData = PieChartData(
        slices = sliceList
    ),
    modifier = Modifier.size(150.dp),
    sliceDrawer = SimpleSliceDrawer(100F)
)

private fun generateSlices(data: List<FundMix>?): List<PieChartData.Slice> {
    val list: MutableList<PieChartData.Slice> = mutableListOf()
    // TODO: tidy this up later
    val colorList = listOf(
        Purple700,
        Purple500,
        boosterPrimary,
        boosterPrimaryVariant,
        boosterSecondary,
        boosterTertiary
    )
    data!!.forEachIndexed { index, fundMix ->
        list.add(PieChartData.Slice(fundMix.percentage!!.toFloat(), colorList[index]))
    }
    return list
}