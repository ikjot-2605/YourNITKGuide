package com.example.yournitkguide

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class LocationListAdapter(private val context: LocationListFragment, private var arrayList : List<Location>) :
    RecyclerView.Adapter<LocationListAdapter.LocationListViewHolder>() {
    class LocationListViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView = view.findViewById<TextView>(R.id.list_item_title)
        val descriptionTextView = view.findViewById<TextView>(R.id.list_item_sub_text)
        val imageView = view.findViewById<ImageView>(R.id.list_item_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationListViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.location_list_item, parent, false)

        return LocationListViewHolder(adapterLayout)

    }

    override fun onBindViewHolder(holder: LocationListViewHolder, position: Int) {
        holder.titleTextView.text = arrayList[position].name
        holder.descriptionTextView.text = arrayList[position].description
        if(arrayList[position].imgUrl.isNotEmpty())
            Glide
                .with(holder.itemView)
                .load(arrayList[position].imgUrl)
                .into(holder.imageView)
        else
            Glide
                .with(holder.itemView)
                .load("https://images.collegedunia.com/public/college_data/images/appImage/15038956701443098931NITSurathkalNew.jpg?mode=stretch")
                .into(holder.imageView)
        holder.itemView.setOnClickListener {
            val action = LocationListFragmentDirections.actionLocationListFragmentToLocationDescriptionFragment(arrayList[position])
            Navigation.findNavController(holder.itemView).navigate(action)
        }
    }

    override fun getItemCount(): Int = arrayList.size
}