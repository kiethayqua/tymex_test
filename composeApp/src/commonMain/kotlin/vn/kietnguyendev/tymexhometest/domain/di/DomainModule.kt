package vn.kietnguyendev.tymexhometest.domain.di

import org.koin.dsl.module
import vn.kietnguyendev.tymexhometest.domain.usecase.GetUserDetailUseCase
import vn.kietnguyendev.tymexhometest.domain.usecase.GetUsersUseCase

val domainModule = module {
    factory { GetUsersUseCase(get()) }
    factory { GetUserDetailUseCase(get()) }
}