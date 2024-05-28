package com.example.vanca.model

import androidx.annotation.DrawableRes

data class TeamMember (
    val name: String,
    val job: String,
    @DrawableRes val profilePicture: Int
)