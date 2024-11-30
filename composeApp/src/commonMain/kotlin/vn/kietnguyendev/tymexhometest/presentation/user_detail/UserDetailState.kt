package vn.kietnguyendev.tymexhometest.presentation.user_detail

import vn.kietnguyendev.tymexhometest.domain.model.UserDetail

data class UserDetailState(
    val isLoading: Boolean = false,
    val data: UserDetail? = null
)