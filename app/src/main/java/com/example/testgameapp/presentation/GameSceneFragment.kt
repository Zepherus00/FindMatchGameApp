package com.example.testgameapp.presentation

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.testgameapp.R
import com.example.testgameapp.databinding.FragmentGameSceneBinding
import com.example.testgameapp.presentation.adapter.GameSceneAdapter
import com.example.testgameapp.utilities.FALSE_SELECT_CARDS
import com.example.testgameapp.utilities.TRUE_SELECT_CARDS
import com.example.testgameapp.utilities.globalMoney
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val PRIZE_KEY = "prize_key_101"

class GameSceneFragment : Fragment() {
    private lateinit var binding: FragmentGameSceneBinding
    private val viewModel by viewModel<GameSceneViewModel>()
    private var timer: CountDownTimer? = null
    private var currentTImerProgress: Int = 0
    private var adapter = GameSceneAdapter { onItemClick(it) }
    private var matchesList = mutableListOf<Int>()
    private var trueCounter = 0
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
        setRecyclerVIew()
        setData()

        viewModel.cardList.observe(viewLifecycleOwner) { cardList ->
            adapter.setList(cardList)
        }
        viewModel.matchesList.observe(viewLifecycleOwner) { vmMatchesList ->
            matchesList = vmMatchesList
        }
        viewModel.trueCounter.observe(viewLifecycleOwner) { vwTrueCounter ->
            trueCounter = vwTrueCounter
        }

        runTimer()
    }

    private fun setRecyclerVIew() {
        with(binding) {
            recyclerFrgGameScene.layoutManager = GridLayoutManager(requireContext(), 4)
            recyclerFrgGameScene.adapter = adapter
        }
    }

    private fun setData() {
        with(binding) {
            moneyCounterFrgGameScene.text = globalMoney.toString()
            txtTimerFrgGameScene.text = getString(R.string.timerNullTime)
        }
    }

    private fun runTimer() {
        timer?.cancel()
        timer = object : CountDownTimer(500000, 1000) {
            override fun onTick(time: Long) {
                currentTImerProgress++
                when {
                    currentTImerProgress < 10 -> binding.txtTimerFrgGameScene.text =
                        getString(R.string.text_timer_null, currentTImerProgress.toString())

                    else -> binding.txtTimerFrgGameScene.text = getString(
                        R.string.text_timer_double_null,
                        currentTImerProgress.toString()
                    )
                }
                if (trueCounter == 10) {
                    onFinish()
                    timer?.cancel()
                }
            }

            override fun onFinish() {
                val bundle = Bundle()
                prize = when {
                    currentTImerProgress < 20 -> 100
                    currentTImerProgress in 21..37 -> 100 - (currentTImerProgress - 20) * 5
                    else -> 10
                }
                globalMoney += prize
                bundle.putInt(PRIZE_KEY, prize)
                findNavController().navigate(
                    R.id.action_gameSceneFragment_to_endGamePopupFragment,
                    bundle
                )
            }
        }.start()
    }

    private fun onItemClick(position: Int) {
        val result = viewModel.onCardClick(position)
        when (result) {
            TRUE_SELECT_CARDS -> {
                adapter.makeUnclickable(matchesList)
                viewModel.setMatchesListEmpty()
            }

            FALSE_SELECT_CARDS -> {
                adapter.hideElements(matchesList)
                viewModel.setMatchesListEmpty()
            }

            else -> {
                Log.d("info", "Nothing")
            }
        }
    }
}