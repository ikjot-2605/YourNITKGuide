package com.example.yournitkguide

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import com.example.yournitkguide.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.GsonBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        var gson = Gson()
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val jsonListLocations : String= applicationContext.assets.open("locations.json").bufferedReader().use{it.readText()}
        print(jsonListLocations)
        val locations: List<Location> = GsonBuilder().create().fromJson(jsonListLocations, Array<Location>::class.java).toList()
        binding.locationListView.adapter =  LocationListAdapter(this,
            locations
        )
    }

}