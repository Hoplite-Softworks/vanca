package com.example.vanca.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vanca.R

@Composable
fun About(
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState()
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = modifier.verticalScroll(scrollState)
    ) {

        AppLogo()

        Text(
            text = "Meet our team!",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 32.dp, bottom = 8.dp)
        )
        Text(
            text = "Our accessibility information app, \"vança\", was designed, developed and promoted by a multi-disciplinary team of students from all over Europe, with diverse backgrounds and skillsets.",
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(16.dp, 0.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.team),
            contentDescription = null,
            modifier = Modifier.padding(16.dp, 64.dp)
        )

        AboutLink{}
    }
}

@Preview
@Composable
fun AboutPreview() {
    About()
}