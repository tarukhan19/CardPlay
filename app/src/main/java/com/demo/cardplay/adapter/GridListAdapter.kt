package com.demo.cardplay.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.cardplay.CardDTO
import com.demo.cardplay.databinding.ItemCardsBinding

class GridListAdapter(
    var gridList: List<CardDTO>,
    var onItemClick: (CardDTO, Int) -> Unit
) : RecyclerView.Adapter<GridListAdapter.GridListAdapterVM>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GridListAdapterVM {
        var binding = ItemCardsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GridListAdapterVM(binding)
    }

    override fun onBindViewHolder(
        holder: GridListAdapterVM,
        position: Int
    ) {
        var item = gridList[position]
        holder.binding.apply {
            cardImg.setImageResource(item.img)
            cardview.setOnClickListener {
                onItemClick(item, position)
            }
        }
    }

    override fun getItemCount(): Int {
        return gridList.size
    }


    inner class GridListAdapterVM(var binding: ItemCardsBinding) :
        RecyclerView.ViewHolder(binding.root)

}