package com.example.vanca.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Station(
    @StringRes val stringResourceId: Int,
    @DrawableRes val imageResourceId: Int
)