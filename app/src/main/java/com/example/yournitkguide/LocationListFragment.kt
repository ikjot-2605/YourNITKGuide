package com.example.yournitkguide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.yournitkguide.databinding.LocationListFragmentBinding
import com.google.gson.Gson
import com.google.gson.GsonBuilder

class LocationListFragment : Fragment() {
    private lateinit var binding:LocationListFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var gson = Gson()
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(inflater,
            R.layout.location_list_fragment, container, false)
        val jsonListLocations : String= context?.assets?.open("locations.json")?.bufferedReader().use{ it?.readText()!! }
        print(jsonListLocations)
        val locations: List<Location> = GsonBuilder().create().fromJson(jsonListLocations, Array<Location>::class.java).toList()
        val adapter = LocationListAdapter(this,
            locations
        )
        binding.locationListView.adapter =adapter
        binding.locationListView.setOnItemClickListener{ _, _, position, _ ->
            val element = adapter.getItem(position)
            val action = LocationListFragmentDirections.actionLocationListFragmentToLocationDescriptionFragment(location = element!!)
            findNavController().navigate(action)
        }
        return binding.root
    }
}