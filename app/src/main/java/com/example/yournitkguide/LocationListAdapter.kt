package com.example.yournitkguide

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class LocationListAdapter(private val context: LocationListFragment, private var arrayList:List<Location>):ArrayAdapter<Location>(context.requireContext(),R.layout.location_list_item,arrayList) {
    // TODO: Change to recycler view
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater :LayoutInflater= LayoutInflater.from(context.context)
        val view : View = inflater.inflate(R.layout.location_list_item,null)
        val titleTextView = view.findViewById<TextView>(R.id.list_item_title)
        val descriptionTextView = view.findViewById<TextView>(R.id.list_item_sub_text)
        titleTextView.text = arrayList[position].name
        descriptionTextView.text = arrayList[position].description
        if(arrayList[position].imgUrl.isNotEmpty())
            Glide
                .with(view)
                .load(arrayList[position].imgUrl)
                .into(view.findViewById<ImageView>(R.id.list_item_image))
        else
            Glide
                .with(view)
                .load("https://images.collegedunia.com/public/college_data/images/appImage/15038956701443098931NITSurathkalNew.jpg?mode=stretch")
                .into(view.findViewById<ImageView>(R.id.list_item_image))
        return view
    }
    fun setData(location: List<Location>) {
        this.arrayList = location
        notifyDataSetChanged()
    }

}