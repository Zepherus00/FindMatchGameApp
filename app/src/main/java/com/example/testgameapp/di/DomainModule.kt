package com.example.testgameapp.di

import com.example.testgameapp.domain.GetCardListUseCase
import org.koin.dsl.module

val domainModule = module {
    factory<GetCardListUseCase> {
        GetCardListUseCase(repository = get())
    }
}