package com.example.vanca.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vanca.R
import com.example.vanca.model.News
import com.example.vanca.model.Station

@Composable
fun Home(
    viewModel: AppViewModel,
    onStationClicked: (Int) -> Unit,
    onNewsClicked: (Int) -> Unit,
    onTeamLinkClicked: () -> Unit,
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState(),
    ) {

    val appUiState by viewModel.appUiState.collectAsState()

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = modifier.verticalScroll(scrollState)
    ) {
        
        AppLogo()

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = {Text(text = "Look up a station")},
            modifier = Modifier.padding(16.dp, 32.dp)
        )

        val headerModifier: Modifier = Modifier
            .background(colorResource(id = R.color.background_color))
            .height(44.dp)
            .fillMaxWidth()
            .wrapContentSize()

        val stationsRecentAndBookmarked: List<Station> = viewModel.loadRecentStations(appUiState.currentUserId).union(viewModel.loadBookmarkedStations(appUiState.currentUserId)).toList()
        StationList(
            stationList = stationsRecentAndBookmarked,
            modifier = Modifier
                .padding(end = 32.dp, start = 32.dp, bottom = 32.dp, top = 32.dp)
                .border(4.dp, Color(0xff99aaff), RoundedCornerShape(16.dp))
                .height(269.dp),
            headerModifier = headerModifier,
            onStationClicked = onStationClicked
        )
        NewsList(
            newsList = viewModel.loadNews(),
            modifier = Modifier
                .padding(32.dp, 32.dp)
                .border(4.dp, Color(0xff99aaff), RoundedCornerShape(16.dp))
                .height(265.dp),
            headerModifier = headerModifier,
            onNewsClicked = onNewsClicked
            )

        AboutLink(onTeamLinkClicked = onTeamLinkClicked)
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StationList(stationList: List<Station>, onStationClicked: (Int) -> Unit, modifier: Modifier = Modifier, headerModifier: Modifier = Modifier) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = modifier
    ) {

        stickyHeader {
            Text(
                text = "Recent & Favorites",
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.shadows_into_light_two)),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = headerModifier
            )
        }

        items(stationList) { station ->
            StationCard(
                station = station,
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
                    .height(60.dp)
                    .clickable { onStationClicked(station.id) }
            )
        }

        item {
            Spacer(modifier = Modifier.padding(8.dp))
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewsList(onNewsClicked: (Int) -> Unit, newsList: List<News>, modifier: Modifier = Modifier, headerModifier: Modifier = Modifier) {

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        stickyHeader {
            Text(
                text = "News & Announcements",
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.shadows_into_light_two)),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = headerModifier
            )
        }

        items(newsList) { news ->
            NewsCard(
                news = news,
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
                    .height(96.dp)
                    .clickable { onNewsClicked(news.id) }
            )
        }
    }

}

@Composable
fun StationCard(station: Station, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Row(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = station.imageResourceId),
                contentDescription = stringResource(id = station.stringResourceId),
                modifier = Modifier
                    .weight(2f),
                contentScale = ContentScale.Crop
            )

            Text(
                text = LocalContext.current.getString(station.stringResourceId),
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .weight(3f)
                    .fillMaxHeight(),
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

@Composable
fun NewsCard(news: News, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Row(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = news.imageResourceId),
                contentDescription = news.title,
                modifier = Modifier
                    .weight(2f),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier
                .weight(3f)) {
                Text(
                    text = news.title,
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .weight(3f)
                        .fillMaxHeight()
                        .padding(12.dp, 4.dp),
                    style = MaterialTheme.typography.headlineSmall
                )

            }

        }
    }
}

@Preview
@Composable
private fun HomePreview() {
    Home(
        viewModel = viewModel(),
        modifier = Modifier
        .fillMaxSize()
        .background(colorResource(id = R.color.background_color)),
        onStationClicked = {},
        onTeamLinkClicked = {},
        onNewsClicked = {}
    )
}
