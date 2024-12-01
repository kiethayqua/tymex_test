package vn.kietnguyendev.tymexhometest.domain

import kotlinx.coroutines.runBlocking
import vn.kietnguyendev.tymexhometest.domain.model.User
import vn.kietnguyendev.tymexhometest.domain.usecase.GetUsersUseCase
import kotlin.test.Test
import kotlin.test.assertEquals

class GetUsersUseCaseTest {
    private val fakeUserRepository = FakeUserRepository()
    private val getUsersUseCase = GetUsersUseCase(fakeUserRepository)

    @Test
    fun `invoke returns list of users from repository`() = runBlocking {
        val expectedUsers = listOf(
            User(username = "Joker", "", ""),
            User(username = "Batman", "", "")
        )
        fakeUserRepository.setUsersResponse(expectedUsers)

        val users = getUsersUseCase(10, 0)

        assertEquals(expectedUsers, users)
    }

    @Test
    fun `invoke returns empty list when repository has no users`() = runBlocking {
        fakeUserRepository.setUsersResponse(emptyList())

        val users = getUsersUseCase(10, 0)

        assertEquals(emptyList(), users)
    }
}