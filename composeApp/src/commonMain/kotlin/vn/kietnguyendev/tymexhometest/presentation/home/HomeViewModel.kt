package vn.kietnguyendev.tymexhometest.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import vn.kietnguyendev.tymexhometest.domain.usecase.GetUsersUseCase
import vn.kietnguyendev.tymexhometest.util.Constants

class HomeViewModel(private val getUsersUseCase: GetUsersUseCase) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        getUsers()
    }

    fun getUsers(isLoadMore: Boolean = false) {
        viewModelScope.launch(Dispatchers.IO) {
            updateLoading(isLoadMore, true)

            val users = getUsersUseCase(perPage = Constants.USERS_PER_PAGE, since = _state.value.page * Constants.USERS_PER_PAGE)

            if (users.isNotEmpty()) {
                val newData = _state.value.data + users
                val newPage = _state.value.page + 1
                _state.update {
                    it.copy(
                        data = newData,
                        page = newPage
                    )
                }
            }

            updateLoading(isLoadMore, false)
        }
    }

    private fun updateLoading(isLoadMore: Boolean, status: Boolean) {
        if (isLoadMore) {
            _state.update {
                it.copy(isLoadingMore = status)
            }
        } else {
            _state.update {
                it.copy(isLoading = status)
            }
        }
    }
}