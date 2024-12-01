package vn.kietnguyendev.tymexhometest.domain

import kotlinx.coroutines.runBlocking
import vn.kietnguyendev.tymexhometest.domain.model.UserDetail
import vn.kietnguyendev.tymexhometest.domain.usecase.GetUserDetailUseCase
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class GetUserDetailUseCaseTest {
    private val fakeUserRepository = FakeUserRepository()
    private val getUserDetailUseCase = GetUserDetailUseCase(fakeUserRepository)

    @Test
    fun `invoke returns user detail when repository has data`() = runBlocking {
        val expectedUserDetail = UserDetail(
            username = "Joker",
            avatarUrl = "test.png",
            pageUrl = "index.html",
            location = "HCM City",
            followers = 100,
            following = 1
        )
        fakeUserRepository.setUserDetailResponse(expectedUserDetail)

        val userDetail = getUserDetailUseCase("Joker")

        assertEquals(expectedUserDetail, userDetail)
    }

    @Test
    fun `invoke returns null when repository has no data`() = runBlocking {
        fakeUserRepository.setUserDetailResponse(null)

        val userDetail = getUserDetailUseCase("bob")

        assertNull(userDetail)
    }
}