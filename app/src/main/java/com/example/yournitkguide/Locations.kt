package com.example.yournitkguide

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity(tableName = "location_table")
@Parcelize
data class Location(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0, val name:String, val description: String, val latitude:Double, val longitude:Double, val imgUrl:String
): Parcelable
