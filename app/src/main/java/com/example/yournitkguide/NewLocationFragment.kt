package com.example.yournitkguide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.yournitkguide.databinding.NewLocationFragmentBinding
import com.example.yournitkguide.viewModel.LocationViewModel


class NewLocationFragment : Fragment() {

    private var _binding :NewLocationFragmentBinding?=null
    private val binding get() = _binding!!
    private lateinit var mLocationViewModel:LocationViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = NewLocationFragmentBinding.inflate(inflater,container,false)
        mLocationViewModel= ViewModelProvider(this).get(LocationViewModel::class.java)
        binding.button.setOnClickListener { insertLocationToDatabase() }
        return binding.root
    }

    private fun insertLocationToDatabase(){
        val name = binding.name.text.toString()
        val description = binding.description.text.toString()
        val latitude = binding.latitude.text.toString().toDouble()
        val longitude = binding.longitude.text.toString().toDouble()
        val location = Location(name=name,description=description, latitude = latitude, longitude = longitude, imgUrl = "https://images.collegedunia.com/public/college_data/images/appImage/15038956701443098931NITSurathkalNew.jpg?mode=stretch")
        print("HII")
        mLocationViewModel.addLocation(location)
        Toast.makeText(activity, "Added Location!", Toast.LENGTH_SHORT).show()
        findNavController().navigateUp()
    }
}