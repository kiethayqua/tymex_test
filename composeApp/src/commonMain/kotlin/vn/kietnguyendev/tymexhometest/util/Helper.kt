package vn.kietnguyendev.tymexhometest.util

import org.koin.core.context.startKoin
import vn.kietnguyendev.tymexhometest.di.appModule

fun initKoin() {
    startKoin {
        modules(appModule)
    }
}