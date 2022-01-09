package com.example.yournitkguide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    lateinit var mLocationViewModel: LocationViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LocationListFragmentBinding.inflate(inflater,container,false)
        val locationList:List<Location> = mutableListOf<Location>()
        val adapter = LocationListAdapter(context=this, arrayList =locationList)
        val listView = binding.locationListView
        listView.adapter = adapter
        mLocationViewModel = ViewModelProvider(this).get(LocationViewModel::class.java)
        mLocationViewModel.readAllData.observe(viewLifecycleOwner, Observer { location->adapter.setData(location) })
        return binding.root
    }
}