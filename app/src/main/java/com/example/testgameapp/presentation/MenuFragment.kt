package com.example.testgameapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.testgameapp.R
import com.example.testgameapp.databinding.FragmentMenuBinding
import com.example.testgameapp.utilities.globalMoney

class MenuFragment : Fragment() {
    private lateinit var binding: FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    private fun setListeners() {
        with(binding) {
            moneyCounterFrgMenu.text = globalMoney.toString()
            btnPlayFrgMenu.setOnClickListener {
                findNavController().navigate(R.id.action_menuFragment_to_gameSceneFragment)
            }
        }
    }
}