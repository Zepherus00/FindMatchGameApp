package com.example.testgameapp.domain

import com.example.testgameapp.data.Repository

class GetCardListUseCase(private val repository: Repository) {
    fun execute(): MutableList<Int> {
        return repository.getCardList()
    }
}