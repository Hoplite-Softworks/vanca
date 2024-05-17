package com.example.vanca.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vanca.model.News


@Composable
fun News(
    viewModel: AppViewModel,
    onTeamLinkClicked: () -> Unit,
    newsId: Int,
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState()
) {

    val news: News = viewModel.loadNews()[newsId-1]

    Column (
        modifier = modifier.verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        AppLogo()

        /*OutlinedTextField(
            value = "",
            onValueChange = { },
            label = { Text(text = "Look up a station") },
            modifier = Modifier.padding(top = 16.dp)
        )*/

        NewsPage(news = news, modifier = Modifier.padding(24.dp, 0.dp))

        AboutLink(
            onTeamLinkClicked = onTeamLinkClicked,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun NewsPage(news: News, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = news.imageResourceId),
            contentDescription = news.title
        )

        Text(
            text = news.title,
            textAlign = TextAlign.Center,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )

        Text(
            text = news.description,
            textAlign = TextAlign.Left,
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 24.dp)
        )

    }
}

@Preview
@Composable
fun NewsPreview() {
    News(
        viewModel = AppViewModel(),
        onTeamLinkClicked = {},
        newsId = 1,
        modifier = Modifier.fillMaxSize()
    )
}