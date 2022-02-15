package com.example.roomsample.recyclerview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomsample.R

class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
    var dataText: TextView? = null

    init{
        dataText = itemView.findViewById(R.id.dataText)
    }
}