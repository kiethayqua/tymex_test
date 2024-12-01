package vn.kietnguyendev.tymexhometest.data.network

import vn.kietnguyendev.tymexhometest.data.model.UserDetailDto
import vn.kietnguyendev.tymexhometest.data.model.UserDto

interface IUserApi {
    suspend fun getUsers(perPage: Int = 20, since: Int = 0): Result<List<UserDto>>

    suspend fun getUserDetail(username: String): Result<UserDetailDto>
}