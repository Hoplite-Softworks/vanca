package com.example.vanca.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vanca.model.Station


@Composable
fun Station(
    viewModel: AppViewModel,
    onTeamLinkClicked: () -> Unit,
    stationId: Int,
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState()
) {

    val station: Station = viewModel.loadAllStations()[stationId - 1]

    Column(
        modifier = modifier.verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        AppLogo()

        OutlinedTextField(
            value = "",
            onValueChange = { },
            label = { Text(text = "Look up a station") },
            modifier = Modifier.padding(top = 16.dp)
        )

        StationPage(station = station, modifier = Modifier.padding(24.dp))

        AboutLink(onTeamLinkClicked = onTeamLinkClicked)
    }
}

@Composable
fun StationPage(station: Station, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = station.imageResourceId),
            contentDescription = stringResource(id = station.stringResourceId)
        )

        Text(
            text = stringResource(id = station.stringResourceId),
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )

        Text(
            text = "Country:   Portugal\nCity:   Porto\nBuilt in:   1915\n",
            textAlign = TextAlign.Left,
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 24.dp)
            //.border(4.dp, Color(0xff99aaff), RoundedCornerShape(16.dp))
        )

        Text(
            text = "Accessiblity-trained staff:   Yes\nElevator:   Yes (STATUS: out of order)\nVança IoT Wristband Dispenser:   No\n",
            textAlign = TextAlign.Left,
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
            //.border(4.dp, Color(0xff99aaff), RoundedCornerShape(16.dp))
        )
    }
}

@Preview
@Composable
fun StationPreview() {
    Station(
        viewModel = AppViewModel(),
        onTeamLinkClicked = {},
        stationId = 1,
        modifier = Modifier
    )
}