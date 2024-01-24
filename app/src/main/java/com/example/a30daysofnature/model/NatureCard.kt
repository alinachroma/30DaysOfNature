package com.example.a30daysofnature.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class NatureCard (
    @DrawableRes val imageResourceId: Int,
    @StringRes val date: Int,
    @StringRes val quote: Int,
    @StringRes val quoteAuthor: Int,
    @StringRes val location: Int,
    val dayNumber: Int,
    )