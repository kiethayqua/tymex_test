package vn.kietnguyendev.tymexhometest.domain.di

import org.koin.dsl.module
import vn.kietnguyendev.tymexhometest.data.repository.UserRepositoryImpl
import vn.kietnguyendev.tymexhometest.domain.repository.UserRepository

val domainModule = module {
    single<UserRepository> {
        UserRepositoryImpl(get())
    }
}