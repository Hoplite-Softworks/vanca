package com.example.vanca.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vanca.R
import com.example.vanca.data.Datasource
import com.example.vanca.model.Station
import com.example.vanca.ui.theme.VancaTheme
import org.jetbrains.annotations.Debug
import java.io.Console

private val TAG: String = "Station"

@Composable
fun Station(modifier: Modifier = Modifier, scrollState: ScrollState = rememberScrollState()) {
    Column (
        modifier = modifier.verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.together),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
        )

        OutlinedTextField(
            value = "",
            onValueChange = { },
            label = { Text(text = "Look up a station") },
            modifier = Modifier.padding(top = 16.dp)
        )

        StationPage(station = Station(R.string.station1, R.drawable.station1), modifier = Modifier.padding(24.dp))
    }
}

@Composable
fun StationPage(station: Station, modifier: Modifier = Modifier) {
    Column(modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Image(
            painter = painterResource(id = station.imageResourceId),
            contentDescription = stringResource(id = station.stringResourceId)
        )

        Text(
            text = "Sao Bento",
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
fun StationPagePreview() {
    StationPage(station = Station(R.string.station1, R.drawable.station1),modifier = Modifier.fillMaxSize())
}

@Preview
@Composable
fun StationPreview() {
    VancaTheme {
        Station(modifier = Modifier.fillMaxSize())
    }
}