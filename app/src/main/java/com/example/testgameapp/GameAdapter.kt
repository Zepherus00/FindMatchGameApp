package com.example.testgameapp

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.testgameapp.databinding.RvItemBinding
import java.util.Collections.emptyList

class GameAdapter(
    private val onClick: (Int) -> Unit
) : RecyclerView.Adapter<GameViewHolder>() {
    private var itemsList = emptyList<Int>()
    private var clickedList = MutableList(20) { true }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        return GameViewHolder(
            RvItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = itemsList.size

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.bg.isVisible = true
        holder.img.isVisible = false

        val img = when (itemsList[position]) {
            1 -> R.drawable.ic_cup
            2 -> R.drawable.ic_lamp
            3 -> R.drawable.ic_mech
            4 -> R.drawable.ic_red_cup
            else -> R.drawable.ic_ship
        }
        holder.img.setImageResource(img)

        holder.itemView.setOnClickListener {
            if (clickedList[position]) {
                holder.bg.isVisible = false
                holder.img.isVisible = true
                onClick(position)
            }
        }
    }

    fun setList(list: List<Int>) {
        itemsList = list
        notifyDataSetChanged()
    }

    fun hideElements(list: List<Int>) {
        list.forEach {
            notifyItemChanged(it)
        }
    }

    fun setUnclickable(list: List<Int>) {
        list.forEach {
            clickedList[it] = false
        }
    }
}

class GameViewHolder(binding: RvItemBinding) : RecyclerView.ViewHolder(binding.root) {
    val bg: AppCompatImageView = binding.bgItem
    val img: AppCompatImageView = binding.imgItem
}