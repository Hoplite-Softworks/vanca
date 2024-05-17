package com.example.vanca.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class News(
    val id: Int,
    val title: String,
    val description: String,
    @DrawableRes val imageResourceId: Int
)