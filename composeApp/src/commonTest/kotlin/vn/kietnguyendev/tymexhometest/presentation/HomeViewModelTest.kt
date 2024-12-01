package vn.kietnguyendev.tymexhometest.presentation

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import vn.kietnguyendev.tymexhometest.domain.FakeUserRepository
import vn.kietnguyendev.tymexhometest.domain.model.User
import vn.kietnguyendev.tymexhometest.domain.usecase.GetUsersUseCase
import vn.kietnguyendev.tymexhometest.presentation.home.HomeViewModel
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)

class HomeViewModelTest {
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var fakeUserRepository: FakeUserRepository
    private lateinit var getUsersUseCase: GetUsersUseCase
    private lateinit var homeViewModel: HomeViewModel

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        fakeUserRepository = FakeUserRepository()
        getUsersUseCase = GetUsersUseCase(fakeUserRepository)
        homeViewModel = HomeViewModel(getUsersUseCase)
    }

    @Test
    fun `init triggers getUsers and updates state`() = runBlocking {
        val expectedUsers = listOf(
            User(username = "joker", pageUrl = "", avatarUrl = ""),
            User(username = "batman", pageUrl = "", avatarUrl = "")
        )
        fakeUserRepository.setUsersResponse(expectedUsers)

        homeViewModel.getUsers()
        delay(250)

        val state = homeViewModel.state.first()

        assertEquals(expectedUsers, state.data)
        assertEquals(1, state.page)
        assertEquals(false, state.isLoading)
    }

    @Test
    fun `getUsers appends users to state when isLoadMore is true`() = runBlocking {
        val initialUsers = listOf(User(username = "joker", pageUrl = "", avatarUrl = ""))
        val additionalUsers = listOf(User(username = "batman", pageUrl = "", avatarUrl = ""))

        fakeUserRepository.setUsersResponse(initialUsers)
        homeViewModel.getUsers()
        delay(250)

        fakeUserRepository.setUsersResponse(additionalUsers)
        homeViewModel.getUsers(isLoadMore = true)
        delay(250)

        val state = homeViewModel.state.first()

        assertEquals(initialUsers + additionalUsers, state.data)
        assertEquals(2, state.page)
        assertEquals(false, state.isLoadingMore)
    }

    @AfterTest
    fun teardown() {
        Dispatchers.resetMain()
    }
}