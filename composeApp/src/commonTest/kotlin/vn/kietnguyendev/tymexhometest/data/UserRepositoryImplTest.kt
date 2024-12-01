package vn.kietnguyendev.tymexhometest.data

import kotlinx.coroutines.runBlocking
import vn.kietnguyendev.tymexhometest.data.model.UserDetailDto
import vn.kietnguyendev.tymexhometest.data.model.UserDto
import vn.kietnguyendev.tymexhometest.data.repository.UserRepositoryImpl
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class UserRepositoryImplTest {

    private val fakeUserApi = FakeUserApi()
    private val userRepository = UserRepositoryImpl(fakeUserApi)

    @Test
    fun `getUsers returns mapped user list on success`() = runBlocking {
        val apiUsers = listOf(
            UserDto(login = "Joker1", avatarUrl = "", htmlUrl = ""),
            UserDto(login = "Joker2", avatarUrl = "", htmlUrl = "")
        )
        fakeUserApi.setUsersResponse(Result.success(apiUsers))

        val users = userRepository.getUsers(10, 0)

        assertEquals(2, users.size)
        assertEquals("Joker1", users[0].username)
        assertEquals("Joker2", users[1].username)
    }

    @Test
    fun `getUsers returns empty list on failure`() = runBlocking {
        fakeUserApi.setUsersResponse(Result.failure(Exception("Network error")))

        val users = userRepository.getUsers(10, 0)

        assertEquals(emptyList(), users)
    }

    @Test
    fun `getUserDetail returns user detail on success`() = runBlocking {
        val apiUserDetail = UserDetailDto(
            login = "Joker",
            avatarUrl = "test-avatar.png",
            htmlUrl = "test-html.html",
            location = "HCM City",
            followers = 1000,
            following = 1
        )
        fakeUserApi.setUserDetailResponse(Result.success(apiUserDetail))

        val userDetail = userRepository.getUserDetail("alice")

        assertEquals("Joker", userDetail?.username)
        assertEquals("HCM City", userDetail?.location)
        assertEquals("test-avatar.png", userDetail?.avatarUrl)
        assertEquals("test-html.html", userDetail?.pageUrl)
        assertEquals(1000, userDetail?.followers)
        assertEquals(1, userDetail?.following)
    }

    @Test
    fun `getUserDetail returns null on failure`() = runBlocking {
        fakeUserApi.setUserDetailResponse(Result.failure(Exception("Network error")))

        val userDetail = userRepository.getUserDetail("test")

        assertNull(userDetail)
    }
}