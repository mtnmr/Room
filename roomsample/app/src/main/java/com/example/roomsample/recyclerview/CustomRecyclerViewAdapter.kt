package com.example.roomsample.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.roomsample.R
import com.example.roomsample.room.SampleEntity
import com.example.roomsample.viewmodel.DataModel
import com.example.roomsample.viewmodel.TextViewModel

class CustomRecyclerViewAdapter(items: List<SampleEntity>) : RecyclerView.Adapter<ViewHolder>() {

    private val items = items

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.one_data, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dataText?.text = items[position].description
    }

    override fun getItemCount(): Int {
        return items.size
    }
}



