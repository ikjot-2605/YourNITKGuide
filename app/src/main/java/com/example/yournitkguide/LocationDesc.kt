package com.example.yournitkguide

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.yournitkguide.databinding.ActivityLocationDescBinding
import com.example.yournitkguide.databinding.ActivityMainBinding

class LocationDesc : AppCompatActivity() {
    private val TAG = "LocationDesc"
    private lateinit var binding: ActivityLocationDescBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_location_desc)
        super.onCreate(savedInstanceState)

        val imgurl=intent.getStringExtra("url")
        val nametext = intent.getStringExtra("name")
        val destext = intent.getStringExtra("desc")
        val image=binding.imageView
        Log.e(TAG, "onCreate: "+imgurl )

        binding.titletxt.setText(nametext)
        binding.desctxt.setText(destext)

        Glide.with(this).load(imgurl).into(image)
    }
}