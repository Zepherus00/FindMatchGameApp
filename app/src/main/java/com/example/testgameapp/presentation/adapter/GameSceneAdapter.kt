package com.example.testgameapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.testgameapp.R
import com.example.testgameapp.databinding.RvItemBinding
import java.util.Collections.emptyList

class GameSceneAdapter(
    private val onClick: (Int) -> Unit
) : RecyclerView.Adapter<GameSceneViewHolder>() {
    private var cardList = emptyList<Int>()
    private var clickedCardList = MutableList(20) { true }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameSceneViewHolder {
        return GameSceneViewHolder(
            RvItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = cardList.size

    override fun onBindViewHolder(holder: GameSceneViewHolder, position: Int) {
        setDefaultVisibility(holder)
        setCardImg(holder, position)
        setCardListener(holder, position)
    }

    private fun setDefaultVisibility(holder: GameSceneViewHolder) {
        holder.bg.isVisible = true
        holder.img.isVisible = false
    }

    private fun setCardImg(holder: GameSceneViewHolder, position: Int) {
        val img = when (cardList[position]) {
            1 -> R.drawable.ic_cup
            2 -> R.drawable.ic_lamp
            3 -> R.drawable.ic_mech
            4 -> R.drawable.ic_red_cup
            else -> R.drawable.ic_ship
        }
        holder.img.setImageResource(img)
    }

    private fun setCardListener(holder: GameSceneViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            if (clickedCardList[position]) {
                holder.bg.isVisible = false
                holder.img.isVisible = true
                onClick(position)
            }
        }
    }

    fun setList(initialCardList: List<Int>) {
        cardList = initialCardList
        notifyDataSetChanged()
    }

    fun hideElements(matchesList: List<Int>) {
        matchesList.forEach {
            notifyItemChanged(it)
        }
    }

    fun makeUnclickable(matchesList: List<Int>) {
        matchesList.forEach {
            clickedCardList[it] = false
        }
    }
}

class GameSceneViewHolder(binding: RvItemBinding) : RecyclerView.ViewHolder(binding.root) {
    val bg: AppCompatImageView = binding.bgRvItem
    val img: AppCompatImageView = binding.imgRvItem
}