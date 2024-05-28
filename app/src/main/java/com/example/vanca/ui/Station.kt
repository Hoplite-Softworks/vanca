package com.example.vanca.ui


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vanca.R
import com.example.vanca.model.Station


@Composable
fun Station(
    viewModel: AppViewModel,
    onTeamLinkClicked: () -> Unit,
    onNoStationFound: () -> Unit,
    stationId: Int,
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState()
) {

    val station: Station? = viewModel.findStationWithId(stationId)
    if (station == null) {
        Toast.makeText(LocalContext.current, "ERROR! No station found.", Toast.LENGTH_SHORT).show()
        onNoStationFound()
    }

    Column(
        modifier = modifier.verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        AppLogo()

        /*OutlinedTextField(
            value = "",
            onValueChange = { },
            label = { Text(text = "Look up a station") },
            modifier = Modifier.padding(top = 16.dp)
        )*/

        StationPage(station = station as Station, modifier = Modifier.padding(24.dp))

        AboutLink(onTeamLinkClicked = onTeamLinkClicked)
    }
}

@Composable
fun StationPage(station: Station, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {
        Card() {
            Image(
                painter = painterResource(id = station.imageResourceId),
                contentDescription = station.stationName,
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(220.dp)
            )
        }


        Text(
            text = station.stationName,
            textAlign = TextAlign.Center,
            fontSize = 25.sp,
            color = colorResource(id = R.color.textColor),
            fontFamily = FontFamily(Font(R.font.open_sans_bold)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )

        /*Text(
            text = "aa",
            textAlign = TextAlign.Left,
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.open_sans_semibold)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 24.dp)
            //.border(4.dp, Color(0xff99aaff), RoundedCornerShape(16.dp))
        )*/

        FeaturesList(station = station, modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 32.dp))

        Text(
            text = station.description,
            textAlign = TextAlign.Left,
            fontSize = 16.sp,
            color = colorResource(id = R.color.textColor),
            fontFamily = FontFamily(Font(R.font.open_sans)),
            modifier = Modifier
                .fillMaxWidth()
            //.border(4.dp, Color(0xff99aaff), RoundedCornerShape(16.dp))
        )
    }
}

@Composable
fun FeaturesList(station: Station, modifier: Modifier) {
    val textSizeSp = 16.sp
    val textSizeDp = with(LocalDensity.current) { textSizeSp.toDp() }
    /*LazyColumn(
        modifier = modifier,
    ) {

        items(station.features.keys.toList()) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "$it ",
                    fontSize = textSizeSp
                )

                Image(
                    painter = painterResource(id = R.drawable.icons8_check_96),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.height(textSizeDp).aspectRatio(1f)
                )
            }
        }
    }*/
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        station.features.forEach {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "${it.key}   ",
                    fontSize = textSizeSp,
                    color = colorResource(id = R.color.textColor),
                    modifier = Modifier
                        .padding(4.dp, 4.dp, 0.dp, 4.dp)
                        .weight(93f)
                )

                Image(
                    painter = painterResource(id = if (it.value) {R.drawable.icons8_check_96} else {R.drawable.icons8_x_64}),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .height(textSizeDp)
                        .padding(0.dp, 4.dp, 4.dp, 4.dp)
                        .aspectRatio(1f)
                        .weight(7f)
                )

            }

            Divider(
                color = Color(0xff888888),
                thickness = 1.dp,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }


}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFF0)
@Composable
fun StationPreview() {
    Station(
        viewModel = AppViewModel(),
        onTeamLinkClicked = {},
        onNoStationFound = {},
        stationId = 1,
        modifier = Modifier
    )
}