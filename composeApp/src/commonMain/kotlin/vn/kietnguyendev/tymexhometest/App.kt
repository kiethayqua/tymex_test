package vn.kietnguyendev.tymexhometest

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import vn.kietnguyendev.tymexhometest.di.koinConfig
import vn.kietnguyendev.tymexhometest.presentation.home.HomeScreen

@Composable
@Preview
fun App() {
    KoinApplication(application = koinConfig) {
        MaterialTheme {
            HomeScreen()
        }
    }
}