package com.example.testgameapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.testgameapp.R
import com.example.testgameapp.databinding.FragmentEndGamePopupBinding

private const val PRIZE_KEY = "prize_key_101"

class EndGamePopupFragment : Fragment() {
    private lateinit var binding: FragmentEndGamePopupBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEndGamePopupBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
        setListeners()
    }

    private fun setData() {
        val prize = arguments?.getInt(PRIZE_KEY) ?: 0
        binding.moneyCounterEndGame.text = prize.toString()
    }

    private fun setListeners() {
        binding.btnHomeEndGame.setOnClickListener {
            findNavController().navigate(R.id.action_endGamePopupFragment_to_menuFragment)
        }
    }
}