package com.example.yournitkguide

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.yournitkguide.databinding.NewLocationFragmentBinding
import com.example.yournitkguide.viewModel.LocationViewModel


class NewLocationFragment : Fragment() {

    // Backup property
    private var _binding : NewLocationFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var mLocationViewModel:LocationViewModel

    // Backup property
    private var _error : MutableList<Boolean> = mutableListOf(true, true, true, true)
    private val error : List<Boolean> get() = _error

    override fun onResume() {
        super.onResume()
        (getActivity()!! as MainActivity).supportActionBar!!.setTitle("Add a location")
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = NewLocationFragmentBinding.inflate(inflater, container, false)
        mLocationViewModel= ViewModelProvider(this)[LocationViewModel::class.java]
        binding.button.setOnClickListener { insertLocationToDatabase() }

        binding.name.editText?.doOnTextChanged { inputText, _, _, _ ->
            val res : Int = validateName(inputText)
            if (res != 0) {
                _error[0] = true
                return@doOnTextChanged
            } else {
                _error[0] = false
                return@doOnTextChanged
            }
        }

        binding.description.editText?.doOnTextChanged { inputText, _, _, _ ->
            val res : Int = validateDescription(inputText)
            if (res != 0) {
                _error[1] = true
                return@doOnTextChanged
            } else {
                _error[1] = false
                return@doOnTextChanged
            }
        }

        binding.latitude.editText?.doOnTextChanged { inputText, _, _, _ ->
            val res : Int = validateLatitude(inputText)
            if (res != 0) {
                _error[2] = true
                return@doOnTextChanged
            } else {
                _error[2] = false
                return@doOnTextChanged
            }
        }

        binding.longitude.editText?.doOnTextChanged { inputText, _, _, _ ->
            val res : Int = validateLongitude(inputText)
            if (res != 0) {
                _error[3] = true
                return@doOnTextChanged
            } else {
                _error[3] = false
                return@doOnTextChanged
            }
        }

        binding.name.editText?.doAfterTextChanged { s ->
            val res : Int = validateName(s.toString())
            if (res != 0) {
                _error[0] = true
                return@doAfterTextChanged
            } else {
                _error[0] = false
                return@doAfterTextChanged
            }
        }

        binding.description.editText?.doAfterTextChanged { s ->
            val res : Int = validateDescription(s.toString())
            if (res != 0) {
                _error[1] = true
                return@doAfterTextChanged
            } else {
                _error[1] = false
                return@doAfterTextChanged
            }
        }

        binding.latitude.editText?.doAfterTextChanged { s ->
            val res : Int = validateLatitude(s.toString())
            if (res != 0) {
                _error[2] = true
                return@doAfterTextChanged
            } else {
                _error[2] = false
                return@doAfterTextChanged
            }
        }

        binding.longitude.editText?.doAfterTextChanged { s ->
            val res : Int = validateLongitude(s.toString())
            if (res != 0) {
                _error[3] = true
                return@doAfterTextChanged
            } else {
                _error[3] = false
                return@doAfterTextChanged
            }
        }
        return binding.root
    }

    private fun validateName(inputText: CharSequence?) : Int {
        if (inputText.toString().isEmpty() || inputText.toString().isBlank()) {
            binding.name.editText?.setError("Name should contain at least one character!")
            return 1
        }
        if (!(inputText.toString().matches(Regex("^[a-zA-Z0-9 ]*\$")))) {
            binding.name.editText?.setError("Name should only contain alphanumeric characters and spaces!")
            return 2
        }
        return 0
    }

    private fun validateDescription(inputText: CharSequence?) : Int {
        if (inputText.toString().isEmpty() || inputText.toString().isBlank()) {
            binding.description.editText?.setError("Description should contain at least one character!")
            return 1
        }
        if (!(inputText.toString().matches(Regex("^[a-zA-Z0-9 ]*\$")))) {
            binding.description.editText?.setError("Description should only contain alphanumeric characters and spaces!")
            return 2
        }
        if (inputText.toString().length < 20) {
            binding.description.editText?.setError("Description should at least be 20 characters!")
            return 3
        }
        return 0
    }

    private fun validateLatitude(inputText: CharSequence?) : Int {
        if (inputText.toString().isEmpty() || inputText.toString().isBlank()) {
            binding.latitude.editText?.setError("Latitude cannot be empty!")
            return 1
        }
        if (!(inputText.toString().matches(Regex("^(\\+|-)?(?:90(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-8][0-9])(?:(?:\\.[0-9]{1,6})?))\$")))) {
            binding.latitude.editText?.setError("Latitude should be a floating point number!")
            return 2
        }
        return 0
    }

    private fun validateLongitude(inputText: CharSequence?) : Int {
        if (inputText.toString().isEmpty() || inputText.toString().isBlank()) {
            binding.latitude.editText?.setError("Longitude cannot be empty!")
            return 1
        }
        if (!(inputText.toString().matches(Regex("^(\\+|-)?(?:180(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-9][0-9]|1[0-7][0-9])(?:(?:\\.[0-9]{1,6})?))\$")))) {
            binding.longitude.editText?.setError("Longitude should be a floating point number!")
            return 2
        }
        return 0
    }

    private fun insertLocationToDatabase(){
        if (_error.contains(true)) {
            Toast.makeText(activity, "Please fix errors before submitting!", Toast.LENGTH_SHORT).show()
            return;
        }
        val name = binding.name.editText?.text.toString()
        val description = binding.description.editText?.text.toString()
        val latitude = binding.latitude.editText?.text.toString().toDouble()
        val longitude = binding.longitude.editText?.text.toString().toDouble()
        val location = Location(name = name, description = description, latitude = latitude,
            longitude = longitude, 
            imgUrl = "https://images.collegedunia.com/public/college_data/images/appImage/15038956701443098931NITSurathkalNew.jpg?mode=stretch")
        mLocationViewModel.addLocation(location)
        Toast.makeText(activity, "Added Location!", Toast.LENGTH_SHORT).show()
        findNavController().navigateUp()
    }
}