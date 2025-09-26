package com.demo.cardplay

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.cardplay.adapter.GridListAdapter
import com.demo.cardplay.adapter.HorizontalListAdapter
import com.demo.cardplay.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var gridList = arrayListOf<CardDTO>(
        CardDTO(1, R.drawable.clubs10, "clubs10"),
        CardDTO(1, R.drawable.diamonds6, "diamonds6"),
        CardDTO(1, R.drawable.hearts4, "hearts4"),
        CardDTO(1, R.drawable.jackofspades, "jackofspades"),
        CardDTO(1, R.drawable.kingofdiamonds, "kingofdiamonds"),
        CardDTO(1, R.drawable.queenofhearts, "queenofhearts"),
    )

    var horizontalList = arrayListOf<CardDTO>(
        CardDTO(1, R.drawable.clubs10, "clubs10"),
        CardDTO(1, R.drawable.diamonds6, "diamonds6"),
        CardDTO(1, R.drawable.hearts4, "hearts4"),
        CardDTO(1, R.drawable.jackofspades, "jackofspades"),
        CardDTO(1, R.drawable.kingofdiamonds, "kingofdiamonds"),
        CardDTO(1, R.drawable.queenofhearts, "queenofhearts"),
    )

    var gridPos: Int? = null
    var horizontalPos: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.shuffleBTN.setOnClickListener {
            shuffle()
        }

        setRecycleview()
    }

    private fun setRecycleview() {

        binding.gridRV.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 3)
            addItemDecoration(ItemDecoration(5))
            adapter = GridListAdapter(gridList) { gridCard, position ->
                gridPos = position
                swap()

            }

        }

        binding.horizontalRV.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(ItemDecoration(10))
            adapter = HorizontalListAdapter(horizontalList) { horizontalCard, position ->
                horizontalPos = position
                swap()
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun shuffle() {
        gridList.shuffle()
        binding.gridRV.adapter?.notifyDataSetChanged()
    }

    fun swap() {
        if (horizontalPos == null || gridPos == null) {
            return
        }

        var temp = horizontalList[horizontalPos!!]
        horizontalList[horizontalPos!!] = gridList[gridPos!!]
        gridList[gridPos!!] = temp

        binding.gridRV.adapter?.notifyItemChanged(gridPos!!)
        binding.horizontalRV.adapter?.notifyItemChanged(horizontalPos!!)

        horizontalPos = null
        gridPos = null
    }
}