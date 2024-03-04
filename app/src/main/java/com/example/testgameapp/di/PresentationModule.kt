package com.example.testgameapp.di

import com.example.testgameapp.presentation.GameSceneViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel<GameSceneViewModel> {
        GameSceneViewModel(getCardListUseCase = get())
    }
}