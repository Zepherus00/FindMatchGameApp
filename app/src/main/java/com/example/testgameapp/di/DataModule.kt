package com.example.testgameapp.di

import com.example.testgameapp.data.Repository
import org.koin.dsl.module

val dataModule = module {
    single<Repository> {
        Repository()
    }
}