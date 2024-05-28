package com.example.vanca.ui

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vanca.R
import com.example.vanca.model.News
import com.example.vanca.model.Station

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Home(
    viewModel: AppViewModel,
    onSearched: (String) -> Unit,
    onStationClicked: (Int) -> Unit,
    onNewsClicked: (Int) -> Unit,
    onTeamLinkClicked: () -> Unit,
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState(),
) {

    val appUiState by viewModel.appUiState.collectAsState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = modifier.verticalScroll(scrollState)
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
            modifier = Modifier.padding(16.dp, 32.dp),
            textStyle = TextStyle(
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.open_sans)),
                color = colorResource(id = R.color.textColor)
            ),
            colors = OutlinedTextFieldDefaults.colors(unfocusedTextColor = colorResource(id = R.color.textColor))
        )

        val headerModifier: Modifier = Modifier
            .background(colorResource(id = R.color.background_color))
            .height(44.dp)
            .fillMaxWidth()
            .wrapContentSize()

        val stationsRecentAndBookmarked: List<Station> =
            viewModel.loadRecentStations(appUiState.currentUserId)
                .union(viewModel.loadBookmarkedStations(appUiState.currentUserId)).toList()
        StationList(
            stationList = stationsRecentAndBookmarked,
            modifier = Modifier
                .padding(end = 32.dp, start = 32.dp, bottom = 32.dp, top = 32.dp)
                .border(2.dp, colorResource(id = R.color.borderColor), RoundedCornerShape(16.dp))
                .height(269.dp),
            headerModifier = headerModifier,
            onStationClicked = onStationClicked,
            headerText = "Recent & Favorites",
            isHeaderVisible = true,
        )
        NewsList(
            newsList = viewModel.loadNews(),
            modifier = Modifier
                .padding(32.dp, 32.dp)
                .border(2.dp, colorResource(id = R.color.borderColor), RoundedCornerShape(16.dp))
                .height(265.dp),
            headerModifier = headerModifier,
            onNewsClicked = onNewsClicked
        )

        AboutLink(onTeamLinkClicked = onTeamLinkClicked)
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StationList(
    stationList: List<Station>,
    onStationClicked: (Int) -> Unit,
    modifier: Modifier = Modifier,
    headerModifier: Modifier = Modifier,
    headerText: String = "",
    isHeaderVisible: Boolean = false,
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = modifier
    ) {
        if (isHeaderVisible) {
            stickyHeader {
                Text(
                    text = headerText,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.open_sans_semibold)),
                    color = colorResource(id = R.color.textColor),
                    textAlign = TextAlign.Center,
                    modifier = headerModifier
                )
            }
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

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewsList(
    newsList: List<News>,
    onNewsClicked: (Int) -> Unit,
    modifier: Modifier = Modifier,
    headerModifier: Modifier = Modifier,
) {

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        stickyHeader {
            Text(
                text = "News & Announcements",
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.open_sans_semibold)),
                color = colorResource(id = R.color.textColor),
                textAlign = TextAlign.Center,
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
        Row(modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.cardBackgroundColor)),) {
            Image(
                painter = painterResource(id = station.imageResourceId),
                contentDescription = station.stationName,
                modifier = Modifier
                    .weight(2f),
                contentScale = ContentScale.Crop
            )

            Text(
                text = station.stationName,
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                lineHeight = 16.sp,
                overflow = TextOverflow.Ellipsis,
                fontFamily = FontFamily(Font(R.font.open_sans_semibold)),
                color = colorResource(id = R.color.textColor),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .weight(3f)
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewsCard(news: News, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Row(modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.cardBackgroundColor)),) {
            Image(
                painter = painterResource(id = news.imageResourceId),
                contentDescription = news.title,
                modifier = Modifier
                    .weight(2f),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .weight(3f)
            ) {
                Text(
                    text = news.title,
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp,
                    lineHeight = 18.sp,
                    fontFamily = FontFamily(Font(R.font.open_sans_semibold)),
                    color = colorResource(id = R.color.textColor),
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .weight(3f)
                        .fillMaxHeight()
                        .padding(12.dp, 4.dp),
                )

                Text(
                    text = "Date: ${DatetoDateString(news.date)}\nRelated station: ${news.relatedStationName}",
                    textAlign = TextAlign.Start,
                    fontSize = 12.sp,
                    lineHeight = 14.sp,
                    fontFamily = FontFamily(Font(R.font.open_sans)),
                    color = colorResource(id = R.color.textColor),
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .weight(3f)
                        .fillMaxSize()
                        .padding(12.dp, 4.dp),
                    style = MaterialTheme.typography.headlineSmall
                )

            }

        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun HomePreview() {
    Home(
        viewModel = AppViewModel(),
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.background_color)),
        onStationClicked = {},
        onTeamLinkClicked = {},
        onNewsClicked = {},
        onSearched = {}
    )
}
