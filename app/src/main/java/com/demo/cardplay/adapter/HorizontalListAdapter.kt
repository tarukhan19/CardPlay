package com.demo.cardplay.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.cardplay.CardDTO
import com.demo.cardplay.databinding.ItemCardsBinding

class HorizontalListAdapter(var cardList: List<CardDTO>, var onItemClick: (CardDTO, Int) -> Unit) :
    RecyclerView.Adapter<HorizontalListAdapter.HorizontalVH>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HorizontalVH {
        val binding = ItemCardsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HorizontalVH(binding)
    }

    override fun onBindViewHolder(
        holder: HorizontalVH,
        position: Int
    ) {
        var item = cardList[position]
        holder.binding.cardImg.setImageResource(item.img)
        holder.binding.cardview.setOnClickListener {
            onItemClick(item, position)
        }
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    inner class HorizontalVH(var binding: ItemCardsBinding) : RecyclerView.ViewHolder(binding.root)
}