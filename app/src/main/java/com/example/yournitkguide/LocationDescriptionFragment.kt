package com.example.yournitkguide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.yournitkguide.databinding.LocationDescriptionFragmentBinding

class LocationDescriptionFragment : Fragment() {
    private lateinit var location: Location
    private lateinit var binding: LocationDescriptionFragmentBinding

    override fun onResume() {
        super.onResume()
        (getActivity()!! as MainActivity).supportActionBar!!.setTitle(location.name)
    }

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
        if(location.imgUrl.isNotEmpty())
            Glide
                .with(this)
                .load(location.imgUrl)
                .transform(CenterCrop())
                .transform(RoundedCorners(36))
                .into(binding.imageView)
        else
            Glide
                .with(this)
                .load("https://images.collegedunia.com/public/college_data/images/appImage/15038956701443098931NITSurathkalNew.jpg?mode=stretch")
                .transform(CenterCrop())
                .transform(RoundedCorners(36))
                .into(binding.imageView)
        return binding.root
    }
}