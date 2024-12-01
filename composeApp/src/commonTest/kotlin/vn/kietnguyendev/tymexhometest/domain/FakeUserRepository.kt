package vn.kietnguyendev.tymexhometest.domain

import vn.kietnguyendev.tymexhometest.domain.model.User
import vn.kietnguyendev.tymexhometest.domain.model.UserDetail
import vn.kietnguyendev.tymexhometest.domain.repository.UserRepository

class FakeUserRepository : UserRepository {
    private var usersResponse: List<User> = emptyList()
    private var userDetailResponse: UserDetail? = null

    fun setUsersResponse(users: List<User>) {
        usersResponse = users
    }

    fun setUserDetailResponse(userDetail: UserDetail?) {
        userDetailResponse = userDetail
    }

    override suspend fun getUsers(perPage: Int, since: Int): List<User> {
        return usersResponse
    }

    override suspend fun getUserDetail(username: String): UserDetail? {
        return userDetailResponse
    }
}