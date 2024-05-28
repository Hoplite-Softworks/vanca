package com.example.vanca.model

import androidx.annotation.DrawableRes
import java.time.LocalDate

data class News(
    val id: Int,
    val title: String,
    val description: String,
    val date: LocalDate,
    val relatedStationId: Int = 0,
    val relatedStationName: String = "-",
    @DrawableRes val imageResourceId: Int,
)