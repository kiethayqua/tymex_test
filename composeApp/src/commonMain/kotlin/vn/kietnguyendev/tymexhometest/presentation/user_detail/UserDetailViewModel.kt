package vn.kietnguyendev.tymexhometest.presentation.user_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import vn.kietnguyendev.tymexhometest.domain.usecase.GetUserDetailUseCase

class UserDetailViewModel(
    private val getUserDetailUseCase: GetUserDetailUseCase,
): ViewModel() {
    private val _state = MutableStateFlow(UserDetailState())
    val state = _state.asStateFlow()

    fun getUserDetail(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(isLoading = true) }
            val user = getUserDetailUseCase(username)
            _state.update { it.copy(isLoading = false, data = user) }
        }
    }
}