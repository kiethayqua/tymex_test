package vn.kietnguyendev.tymexhometest.di

import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.includes
import org.koin.dsl.koinConfiguration
import org.koin.dsl.module
import vn.kietnguyendev.tymexhometest.data.di.dataModule
import vn.kietnguyendev.tymexhometest.domain.di.domainModule
import vn.kietnguyendev.tymexhometest.presentation.di.presentationModule

expect fun nativeConfig() : KoinAppDeclaration

val koinConfig = koinConfiguration {
    includes(nativeConfig())
    modules(appModule)
}

val appModule = module {
    includes(dataModule, domainModule, presentationModule)
}