package com.example.roomsample.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.roomsample.R
import com.example.roomsample.viewmodel.DataModel
import com.example.roomsample.viewmodel.TextViewModel

class CustomRecyclerViewAdapter(fragment: Fragment,
                                viewModel: TextViewModel)
    : RecyclerView.Adapter<ViewHolder>() {
    init {
        viewModel.dataList.observe(fragment, Observer {items ->
            setItems(items)
        })
    }

    private val items = mutableListOf<DataModel>()

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

    private fun setItems(items:List<DataModel>){
        this.items.clear()
        items.forEach(){ item ->
            this.items.add(item)
        }

        notifyDataSetChanged()
    }
}



