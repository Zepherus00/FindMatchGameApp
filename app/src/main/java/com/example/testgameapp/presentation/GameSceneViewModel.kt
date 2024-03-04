package com.example.testgameapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testgameapp.domain.GetCardListUseCase
import com.example.testgameapp.utilities.FALSE_SELECT_CARDS
import com.example.testgameapp.utilities.NOTHING_SELECT_CARDS
import com.example.testgameapp.utilities.TRUE_SELECT_CARDS

class GameSceneViewModel(
    getCardListUseCase: GetCardListUseCase
) : ViewModel() {
    private val _cardList = MutableLiveData<MutableList<Int>>()
    val cardList: LiveData<MutableList<Int>> = _cardList

    private val _matchesList = MutableLiveData<MutableList<Int>>()
    val matchesList: LiveData<MutableList<Int>> = _matchesList

    private val _trueCounter = MutableLiveData<Int>()
    val trueCounter: LiveData<Int> = _trueCounter

    init {
        _cardList.value = getCardListUseCase.execute()
        _matchesList.value = mutableListOf()
        _trueCounter.value = 0
    }

    fun onCardClick(position: Int): String {
        _matchesList.value?.add(position)
        val tempMatchList = _matchesList.value ?: emptyList()
        val tempCardList = _cardList.value ?: emptyList()
        return if (matchesList.value?.size == 2
            && tempMatchList.isNotEmpty()
            && tempCardList.isNotEmpty()
        ) {
            if (tempCardList[tempMatchList[0]] == tempCardList[tempMatchList[1]]) {
                _trueCounter.value = _trueCounter.value?.plus(1) ?: 1
                TRUE_SELECT_CARDS
            } else {
                FALSE_SELECT_CARDS
            }
        } else NOTHING_SELECT_CARDS
    }

    fun setMatchesListEmpty() {
        _matchesList.value = mutableListOf()
    }
}