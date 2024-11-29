package vn.kietnguyendev.tymexhometest.domain.repository

import vn.kietnguyendev.tymexhometest.domain.model.User
import vn.kietnguyendev.tymexhometest.domain.model.UserDetail

interface UserRepository {
    suspend fun getUsers(perPage: Int, since: Int): List<User>

    suspend fun getUserDetail(username: String): UserDetail?
}