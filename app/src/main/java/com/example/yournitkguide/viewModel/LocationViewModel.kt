package com.example.yournitkguide.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.kotlinroomdatabase.data.LocationDatabase
import com.example.yournitkguide.Location
import com.example.yournitkguide.repository.LocationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocationViewModel(application: Application):AndroidViewModel(application) {

    val readAllData : LiveData<List<Location>>

    val isDBEmpty:Int

    val repository: LocationRepository

    init{
        val locationDao = LocationDatabase.getDatabase(application).locationDao()
        repository = LocationRepository(locationDao)
        readAllData = repository.readAllData
        isDBEmpty = repository.isDBEmpty
    }

    fun addLocation(location: Location){
        viewModelScope.launch(Dispatchers.IO) {repository.addLocation(location)}
    }

    fun updateLocation(location: Location) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateLocation(location)
        }
    }

    fun deleteLocation(location: Location) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteLocation(location)
        }
    }

}