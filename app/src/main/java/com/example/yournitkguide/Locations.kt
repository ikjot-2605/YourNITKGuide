package com.example.yournitkguide

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Location(
    val id:Int, val name:String, val description: String, val lat_long:Pair<Double,Double>, val imgUrl:String
): Parcelable
