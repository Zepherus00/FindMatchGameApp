package com.example.testgameapp.data

class Repository {
    fun getCardList(): MutableList<Int> {
        val initialCardList =
            mutableListOf(1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5)
        initialCardList.shuffle()
        return initialCardList
    }
}