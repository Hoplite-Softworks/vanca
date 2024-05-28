package com.example.vanca.ui

import android.media.tv.TvContract.Channels.Logo
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.vanca.R
import java.time.LocalDate

@Composable
fun AppLogo(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Spacer(
            modifier = Modifier
                .height(32.dp)
                .fillMaxWidth()
                .background(colorResource(id = R.color.logo_color))
        )
        Image(
            painter = painterResource(id = R.drawable.together),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
        )
    }

}

@Composable
fun AboutLink(modifier: Modifier = Modifier, onTeamLinkClicked: () -> Unit) {
    Text(
        text = "D-PBL - Group C - 2024\nMeet the team!",
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        color = Color(0xFF000055),
        modifier = modifier
            .padding(top = 12.dp, bottom = 20.dp)
            .clickable { onTeamLinkClicked() }
    )
}

fun levenshteinDistance(lhs: String, rhs: String): Int {
    val lhsLength = lhs.length
    val rhsLength = rhs.length

    var cost = Array(lhsLength + 1) { it }
    var newCost = Array(lhsLength + 1) { 0 }

    for (i in 1..rhsLength) {
        newCost[0] = i

        for (j in 1..lhsLength) {
            val match = if (lhs[j - 1] == rhs[i - 1]) 0 else 1

            val costReplace = cost[j - 1] + match
            val costInsert = cost[j] + 1
            val costDelete = newCost[j - 1] + 1

            newCost[j] = minOf(costInsert, costDelete, costReplace)
        }

        val swap = cost
        cost = newCost
        newCost = swap
    }

    return cost[lhsLength]
}

@RequiresApi(Build.VERSION_CODES.O)
fun DatetoDateString(date: LocalDate):String {
    return "${date.dayOfMonth}/${date.monthValue}/${date.year}"
}
