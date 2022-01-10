package com.example.yournitkguide

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.yournitkguide.databinding.LocationDescriptionFragmentBinding
import com.example.yournitkguide.databinding.LocationListFragmentBinding
import com.example.yournitkguide.viewModel.LocationViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder

class LocationListFragment : Fragment() {
    private var _binding: LocationListFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var mLocationViewModel: LocationViewModel

    override fun onResume() {
        super.onResume()
        (getActivity()!! as MainActivity).supportActionBar!!.setTitle("List of locations")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LocationListFragmentBinding.inflate(inflater, container,false)
        var locationList : List<Location> = mutableListOf<Location>(Location(name = "name",description = "description", latitude = 1.1, longitude = 2.2, imgUrl = "https://images.collegedunia.com/public/college_data/images/appImage/15038956701443098931NITSurathkalNew.jpg?mode=stretch"))
        mLocationViewModel = ViewModelProvider(this)[LocationViewModel::class.java]
        if(mLocationViewModel.isDBEmpty==0){
            val str : String? = context?.assets?.open("locations.json")?.bufferedReader()?.use { it.readText() }
            val list:List<Location> = GsonBuilder().create().fromJson(context?.assets?.open("locations.json")?.bufferedReader().use{ it?.readText()!! },
                Array<Location>::class.java).toList()
            for (location in list){
                Log.d("ADD",location.toString())
                mLocationViewModel.addLocation(location)
            }
            Log.d("Over","Done Adding")
            Toast.makeText(activity, "Imported Default Locations!", Toast.LENGTH_SHORT).show()
        }
        mLocationViewModel.readAllData.observe(viewLifecycleOwner, Observer { location->
            Log.d("TAG", location.toString())
            locationList = location
            val adapter = LocationListAdapter(context = this, arrayList = locationList)
            val listView = binding.locationListView
            listView.setOnItemClickListener { parent, view, position, id ->
                val action = LocationListFragmentDirections.actionLocationListFragmentToLocationDescriptionFragment(locationList[position])
                findNavController().navigate(action)

            }
            listView.adapter = adapter
        })
        Log.d("TAG", locationList.toString())

        return binding.root
    }
}