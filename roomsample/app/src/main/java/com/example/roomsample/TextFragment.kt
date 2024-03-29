package com.example.roomsample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomsample.databinding.FragmentTextBinding
import com.example.roomsample.recyclerview.CustomRecyclerViewAdapter
import com.example.roomsample.room.SampleEntity
import com.example.roomsample.viewmodel.DataModel
import com.example.roomsample.viewmodel.TextViewModel

class TextFragment : Fragment() {

    private lateinit var binding : FragmentTextBinding

    private val viewModel:TextViewModel by activityViewModels()

    private var dataList = mutableListOf<SampleEntity>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTextBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CustomRecyclerViewAdapter(dataList)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        viewModel.dataList.observe(this.viewLifecycleOwner){
            dataList.clear()
            it.forEach { item ->
                dataList.add(item)
            }
            adapter.notifyDataSetChanged()
        }

//        binding.button.setOnClickListener {
//            viewModel.changeText()
//            viewModel.saveSampleData(DataModel(viewModel.textData.value.toString()))
//            viewModel.loadSampleDataList()
//        }
    }

}