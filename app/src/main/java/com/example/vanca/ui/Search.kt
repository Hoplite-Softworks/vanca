package com.example.vanca.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vanca.R

@Composable
fun Search(
    viewModel: AppViewModel,
    stationQuery: String,
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState(),
    onTeamLinkClicked: () -> Unit,
    onStationClicked: (Int) -> Unit,
    onSearched: (String) -> Unit,
) {
    val resultsList = viewModel.searchStations(stationQuery)
    viewModel.resetStationInput()

    Column(
        modifier = modifier.verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        AppLogo()

        OutlinedTextField(
            value = viewModel.stationSearched,
            onValueChange = { viewModel.updateStationInput(it) },
            label = { Text(text = "Look up a station") },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                viewModel.searchStationInitialized(onSearched)
            }),
            modifier = Modifier.padding(16.dp, 32.dp)
        )

        /*Text(
            text = "Number of results: ${resultsList.size}"
        )*/

        StationList(
            stationList = resultsList,
            onStationClicked = onStationClicked,
            isHeaderVisible = true,
            headerText = "Number of results: ${resultsList.size}",
            headerModifier = Modifier.background(colorResource(id = R.color.background_color))
            .height(44.dp)
            .fillMaxWidth()
            .wrapContentSize(),
            modifier = Modifier
                .padding(24.dp)
                .border(2.dp, Color(0xff99aaff), RoundedCornerShape(16.dp))
                .height(385.dp),
        )

        AboutLink(onTeamLinkClicked = onTeamLinkClicked)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFF0)
@Composable
fun SearchPreview() {
    Search(
        viewModel = AppViewModel(),
        stationQuery = "",
        onTeamLinkClicked = { },
        onStationClicked = {},
        onSearched = {},
    )
}