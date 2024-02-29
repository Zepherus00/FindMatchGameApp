package com.example.testgameapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.testgameapp.databinding.FragmentEndGamePopupBinding

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
        val prize = arguments?.getInt("prize") ?: 0
        money += prize
        binding.txtPrize.text = prize.toString()

        binding.btnHome.setOnClickListener {
            findNavController().navigate(R.id.action_endGamePopupFragment_to_menuFragment)
        }
    }
}