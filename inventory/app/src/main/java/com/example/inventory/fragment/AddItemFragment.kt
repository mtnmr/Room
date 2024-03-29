package com.example.inventory.fragment

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.inventory.InventoryApplication
import com.example.inventory.InventoryViewModel
import com.example.inventory.InventoryViewModelFactory
import com.example.inventory.data.Item
import com.example.inventory.databinding.FragmentAddItemBinding


class AddItemFragment : Fragment() {

    private val viewModel: InventoryViewModel by activityViewModels {
        InventoryViewModelFactory(
            (activity?.application as InventoryApplication).database.itemDao()
        )
    }

    lateinit var item: Item

    private val navigationArgs: ItemDetailFragmentArgs by navArgs()

    private var _binding:FragmentAddItemBinding?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddItemBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = navigationArgs.itemId
        if (id > 0){
            viewModel.retrieveItem(id).observe(this.viewLifecycleOwner){ selectedItem ->
                item = selectedItem
                bind(item)
            }
        }else{
            binding.saveAction.setOnClickListener {
                addNewItem()
            }
        }
    }

    private fun bind(item:Item){
        val price = "%.2f".format(item.itemPrice)

        binding.apply {
            itemName.setText(item.itemName, TextView.BufferType.SPANNABLE)
            itemPrice.setText(price, TextView.BufferType.SPANNABLE)
            itemCount.setText(item.quantityInStock.toString(), TextView.BufferType.SPANNABLE)

            saveAction.setOnClickListener { updateItem() }
        }
    }

    //全てに入力されているか確認して新しくItemを登録する
    private fun isEntryValid():Boolean{
        return viewModel.isEntryValid(
            binding.itemName.text.toString(),
            binding.itemPrice.text.toString(),
            binding.itemCount.text.toString()
        )
    }

    private fun addNewItem() {
        if (isEntryValid()) {
            viewModel.addNewItem(
                binding.itemName.text.toString(),
                binding.itemPrice.text.toString(),
                binding.itemCount.text.toString(),
            )
            val action = AddItemFragmentDirections.actionAddItemFragmentToItemListFragment()
            findNavController().navigate(action)
        }
    }

    //navArgsで受け取ったid > 0の時、既にあるItemを更新する
    private fun updateItem(){
        if (isEntryValid()){
            viewModel.updateItem(
                this.navigationArgs.itemId,
                this.binding.itemName.text.toString(),
                this.binding.itemPrice.text.toString(),
                this.binding.itemCount.text.toString()
            )

            val action = AddItemFragmentDirections.actionAddItemFragmentToItemListFragment()
            this.findNavController().navigate(action)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        //キーボードを非表示にしてからフラグメントを破棄する
        val inputMethodManager = requireActivity().getSystemService(INPUT_METHOD_SERVICE) as
                InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
        _binding = null
    }


}