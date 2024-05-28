package com.example.vanca.model

import androidx.annotation.DrawableRes
import com.example.vanca.R

data class Station(
    val id: Int,
    //@StringRes val stringResourceId: Int,
    val stationName: String,
    val description: String,
    val features: Map<String, Boolean>,
    @DrawableRes val imageResourceId: Int = R.drawable.metro_do_porto
)