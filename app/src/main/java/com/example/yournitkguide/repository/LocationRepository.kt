package com.example.yournitkguide.repository

import LocationDao
import androidx.lifecycle.LiveData
import com.example.yournitkguide.Location

class LocationRepository(private val locationDao:LocationDao) {
    val readAllData : LiveData<List<Location>> = locationDao.readAllData()

    suspend fun addLocation(location: Location){
        locationDao.addLocation(location)
    }
    suspend fun updateLocation(location: Location){
        locationDao.updateLocation(location)
    }
    suspend fun deleteLocation(location: Location){
        locationDao.deleteLocation(location)
    }

}