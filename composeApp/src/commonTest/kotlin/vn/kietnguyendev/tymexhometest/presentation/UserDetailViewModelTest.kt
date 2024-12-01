package vn.kietnguyendev.tymexhometest.presentation

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import vn.kietnguyendev.tymexhometest.domain.FakeUserRepository
import vn.kietnguyendev.tymexhometest.domain.model.UserDetail
import vn.kietnguyendev.tymexhometest.domain.usecase.GetUserDetailUseCase
import vn.kietnguyendev.tymexhometest.presentation.user_detail.UserDetailViewModel
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class UserDetailViewModelTest {
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var fakeUserRepository: FakeUserRepository
    private lateinit var getUserDetailUseCase: GetUserDetailUseCase
    private lateinit var userDetailViewModel: UserDetailViewModel

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        fakeUserRepository = FakeUserRepository()
        getUserDetailUseCase = GetUserDetailUseCase(fakeUserRepository)
        userDetailViewModel = UserDetailViewModel(getUserDetailUseCase)
    }

    @Test
    fun `getUserDetail updates state with loading and user data`() = runBlocking {
        val username = "testUser"
        val expectedUserDetail = UserDetail(username, "", "", "HCM City", 100, 1)
        fakeUserRepository.setUserDetailResponse(expectedUserDetail)

        userDetailViewModel.getUserDetail(username)
        delay(250)

        val state = userDetailViewModel.state.first()

        assertEquals(false, state.isLoading)
        assertEquals(expectedUserDetail, state.data)
    }
}