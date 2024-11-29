package vn.kietnguyendev.tymexhometest.di

import org.koin.dsl.koinConfiguration

actual fun nativeConfig() = koinConfiguration {
    printLogger()
}