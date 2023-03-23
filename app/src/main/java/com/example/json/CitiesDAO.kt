package com.example.json

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CitiesDAO {

    @Insert
    suspend fun insertCity(cities: Cities)

    @Update
    suspend fun updateCity(cities: Cities)

    @Delete
    suspend fun deleteCity(cities: Cities)

    @Query("SELECT * FROM cities")
    fun getCities() : LiveData<List<Cities>>

    @Query("SELECT * FROM cities WHERE city LIKE :query ORDER BY popular DESC LIMIT 30")
    fun getCitiesByName(query: String): List<Cities>

    @Query("DELETE FROM cities")
    fun deleteAll()

}