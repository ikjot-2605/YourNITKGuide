package com.example.yournitkguide

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "location_table")
data class Location(
    @PrimaryKey
    val id:Int, val name:String, val description: String, val latitude:Double, val longitude:Double, val imgUrl:String
): Parcelable
