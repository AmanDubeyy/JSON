package com.example.json

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CitiesViewModel (application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext

    private val database = CitiesDatabase.getDatabase(context)

     private val retrofit = Retrofit.Builder()
        .baseUrl("http://cdn1.acedms.com/photos/data/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(APIRequest::class.java)

    fun deleteAllCities() {
        viewModelScope.launch {
            database.citiesDAO().deleteAll()
        }
    }

    fun getCitiesFromAPI() {
        viewModelScope.launch(Dispatchers.IO) {

            try {
                val response = api.getData()

                response.data.popular.map{
                    database.citiesDAO().insertCity(Cities(0, it.key, it.value, 1))
                }

                response.data.other.map{
                    database.citiesDAO().insertCity(Cities(0, it.key, it.value, 0))
                }



            } catch (e: Exception) {
                Log.e("data", "Error searching for results: ${e.message}")
            }
        }
    }

    fun getCitiesByName(query: String): List<Cities> {
        return database.citiesDAO().getCitiesByName(query)
    }
}
