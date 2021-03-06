package com.example.yournitkguide

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.yournitkguide.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.GsonBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        // Instantiate the navController using the NavHostFragment
        navController = navHostFragment.navController
        // Make sure actions in the ActionBar get propagated to the NavController
        setupActionBarWithNavController(navController)
        supportActionBar?.title = "Locations of NITK"
        supportActionBar?.elevation = 7F
        binding.fab.setImageResource(R.drawable.ic_baseline_add_24)
        binding.fab.setOnClickListener{view->
            val action = LocationListFragmentDirections.actionLocationListFragmentToNewLocationFragment()
            navController.navigate(action)
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onResume() {
        super.onResume()
        supportActionBar?.title = "Locations of NITK"
    }

}