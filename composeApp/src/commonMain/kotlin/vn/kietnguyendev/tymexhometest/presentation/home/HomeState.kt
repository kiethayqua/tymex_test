package vn.kietnguyendev.tymexhometest.presentation.home

import vn.kietnguyendev.tymexhometest.domain.model.User

data class HomeState(
    val isLoading: Boolean = false,
    val data: List<User> = emptyList(),
    val page: Int = 1
)