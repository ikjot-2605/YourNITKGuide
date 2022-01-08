package com.example.yournitkguide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.yournitkguide.databinding.LocationDescriptionFragmentBinding
import com.example.yournitkguide.databinding.LocationListFragmentBinding
import com.google.gson.Gson
import com.google.gson.GsonBuilder

class LocationDescriptionFragment : Fragment() {
    private lateinit var location: Location
    private lateinit var binding: LocationDescriptionFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            location = it.get("location") as Location
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(inflater,
            R.layout.location_description_fragment, container, false)
        binding.desctxt.text = location.description
        binding.titletxt.text = location.name

        return binding.root
    }
}