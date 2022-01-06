package com.example.yournitkguide

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class LocationListAdapter(private val context: Activity, private val arrayList:List<Location>):ArrayAdapter<Location>(context,R.layout.location_list_item,arrayList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater :LayoutInflater= LayoutInflater.from(context)
        val view:View = inflater.inflate(R.layout.location_list_item,null)
        val titleTextView = view.findViewById<TextView>(R.id.title)
        val descriptionTextView = view.findViewById<TextView>(R.id.description)
        titleTextView.text = arrayList[position].name
        descriptionTextView.text = arrayList[position].description
        return view
    }

}