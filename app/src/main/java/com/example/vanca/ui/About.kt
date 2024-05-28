package com.example.vanca.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

@Composable
fun About(
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState(),
    viewModel: AppViewModel
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = modifier.verticalScroll(scrollState)
    ) {

        AppLogo()

        Text(
            text = "Meet our team!",
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(Font(R.font.open_sans_bold)),
            fontSize = 18.sp,
            lineHeight = 20.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 32.dp, bottom = 0.dp)
        )
        Text(
            text = stringResource(R.string.about_us),
            textAlign = TextAlign.Justify,
            fontFamily = FontFamily(Font(R.font.open_sans)),
            fontSize = 14.sp,
            lineHeight = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 0.dp)
        )

        TeamMembers(viewModel = viewModel, modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 48.dp))


        AboutLink {}
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TeamMembers(viewModel: AppViewModel, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        FlowRow(
            horizontalArrangement = Arrangement.Start,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
        ) {
            viewModel.loadTeamMembers().forEach() { member ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.width(150.dp).height(200.dp).padding(8.dp).wrapContentSize()
                ) {
                    Image(
                        painter = painterResource(id = member.profilePicture),
                        contentDescription = member.name,
                        modifier = Modifier.height(50.dp)
                    )
                    Text(
                        text = member.name,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily(Font(R.font.open_sans_semibold)),
                        fontSize = 12.sp,
                        lineHeight = 16.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Text(
                        text = member.job,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily(Font(R.font.open_sans)),
                        fontSize = 10.sp,
                        lineHeight = 12.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }

}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFF0)
@Composable
fun AboutPreview() {
    About(viewModel = AppViewModel())
}