package vn.kietnguyendev.tymexhometest.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import vn.kietnguyendev.tymexhometest.domain.repository.UserRepository

class HomeViewModel(private val userRepository: UserRepository): ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        getUsers()
    }

    fun getUsers(page: Int = 1) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update {
                it.copy(isLoading = true)
            }
            val users = userRepository.getUsers(perPage = 20, since = page)
            _state.update {
                it.copy(
                    isLoading = false,
                    data = users,
                    page = page
                )
            }
        }
    }
}