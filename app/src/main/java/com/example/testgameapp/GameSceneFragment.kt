package com.example.testgameapp

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.testgameapp.databinding.FragmentGameSceneBinding

class GameSceneFragment : Fragment() {
    private lateinit var binding: FragmentGameSceneBinding
    private var timer: CountDownTimer? = null
    private var currentProgress: Int = 0
    private var adapter = GameAdapter { onItemClick(it) }
    private var nullList = mutableListOf<Int>()
    private var countLis = mutableListOf<Int>()
    private var trueCount = 0

    private var prize = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameSceneBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 4)
        binding.recyclerView.adapter = adapter
        binding.txtMoneyCounterGameScene.text = money.toString()
        binding.txtTimer.text = "00:00"

        repeat(4) {
            nullList.add(1)
        }
        repeat(4) {
            nullList.add(2)
        }
        repeat(4) {
            nullList.add(3)
        }
        repeat(4) {
            nullList.add(4)
        }
        repeat(4) {
            nullList.add(5)
        }
        nullList = nullList.shuffled().toMutableList()

        adapter.setList(nullList)

        runTimer()
    }

    private fun onItemClick(position: Int) {
        countLis.add(position)
        if (countLis.size == 2) {
            if (nullList[countLis[0]] == nullList[countLis[1]]) {
                trueCount++
                adapter.setUnclickable(countLis)
            } else {
                adapter.hideElements(countLis)
            }
            countLis = mutableListOf()
        }
    }

    private fun runTimer() {
        with(binding) {
            timer?.cancel()
            timer = object : CountDownTimer(500000, 1000) {
                override fun onTick(time: Long) {
                    currentProgress++
                    when {
                        currentProgress < 10 -> {
                            binding.txtTimer.text =
                                getString(R.string.text_timer_null, currentProgress.toString())
                        }

                        else -> {
                            binding.txtTimer.text = getString(
                                R.string.text_timer_double_null,
                                currentProgress.toString()
                            )
                        }
                    }
                    if (trueCount == 10) {
                        onFinish()
                        timer?.cancel()
                    }
                }

                override fun onFinish() {
                    val bundle = Bundle()
                    prize = when {
                        currentProgress < 20 -> 100
                        currentProgress in 21..37 -> {
                            100 - (currentProgress - 20) * 5
                        }

                        else -> 10
                    }
                    bundle.putInt("prize", prize)
                    findNavController().navigate(
                        R.id.action_gameSceneFragment_to_endGamePopupFragment,
                        bundle
                    )
                }
            }.start()
        }
    }
}