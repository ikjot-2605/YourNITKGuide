package com.example.yournitkguide.data
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.yournitkguide.Location

@Dao
interface LocationDao{
    @Insert
    suspend fun addLocation(location: Location)

    @Update
    suspend fun updateLocation(location: Location)

    @Delete
    suspend fun deleteLocation(location: Location)

    @Query("SELECT * from location_table ORDER BY id ASC") // <- Add a query to fetch all users (in user_table) in ascending order by their IDs.
    fun readAllData(): LiveData<List<Location>> // <- This means function return type is List. Specifically, a List of Users.

    @Query("SELECT COUNT(*) from location_table")
    fun isDBEmpty(): Int
}