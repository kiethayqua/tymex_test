package vn.kietnguyendev.tymexhometest.presentation.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import vn.kietnguyendev.tymexhometest.presentation.home.HomeViewModel
import vn.kietnguyendev.tymexhometest.presentation.user_detail.UserDetailViewModel

val presentationModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { UserDetailViewModel(get()) }
}