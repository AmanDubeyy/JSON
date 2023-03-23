package com.example.json

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.json.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel : CitiesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[CitiesViewModel::class.java]

        //---------------------------FILL DB-----------------------
        //viewModel.deleteAllCities()
        viewModel.getCitiesFromAPI()

        //---------------------------------SEARCH------------------------------------------

        binding.seacrh.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString() + "%"

                val cities = viewModel.getCitiesByName(query)
                showList(cities)
            }
        })
    }

    fun showList(cities: List<Cities>){
        val adapter = CityAdapter(cities)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

        adapter.onClickItem = {
            val intent = Intent(this, DetailActivity :: class.java)
            intent.putExtra("data", it)
            startActivity(intent)
        }
    }
}