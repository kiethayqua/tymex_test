package vn.kietnguyendev.tymexhometest.presentation.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import vn.kietnguyendev.tymexhometest.presentation.home.HomeViewModel

val presentationModule = module {
    viewModel { HomeViewModel(get()) }
}