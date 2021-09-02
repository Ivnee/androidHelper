package com.geek.infoandroid.Kotlin.materialDesign.les6

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.fortestcode.ui.main.Data
import com.example.fortestcode.ui.main.ItemTouchHelperCallback
import com.example.fortestcode.ui.main.OnItemClickListener
import com.example.fortestcode.ui.main.RecyclerViewAdapterr
import com.geek.infoandroid.R
import com.geek.infoandroid.databinding.MainFragment3Binding

class MainFragment : Fragment(R.layout.main_fragment3) {
    companion object {
        const val TYPE_EARTH = 0
        const val TYPE_MARS = 1
        const val TYPE_HEADER = 2
    }

    var viewBinding: MainFragment3Binding? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = MainFragment3Binding.bind(view)
        val safeBinding = viewBinding ?: return
        val data = arrayListOf(
            Pair(Data("Mars", ""), false)
        )
        val adapter = RecyclerViewAdapterr(object : OnItemClickListener {
            override fun onItemClick(data: Data) {
                Toast.makeText(requireContext(), data.someText, Toast.LENGTH_SHORT).show()
            }
        }, data)
        data.add(0, Pair(Data("Header"), false))
        safeBinding.recyclerView.adapter = adapter
        safeBinding.recyclerFAB.setOnClickListener {
            adapter.appendItem()
        }
        ItemTouchHelper(ItemTouchHelperCallback(adapter)).attachToRecyclerView(safeBinding.recyclerView)
    }


}
