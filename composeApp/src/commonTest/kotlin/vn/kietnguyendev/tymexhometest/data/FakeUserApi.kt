package vn.kietnguyendev.tymexhometest.data

import vn.kietnguyendev.tymexhometest.data.model.UserDetailDto
import vn.kietnguyendev.tymexhometest.data.model.UserDto
import vn.kietnguyendev.tymexhometest.data.network.IUserApi

class FakeUserApi: IUserApi {
    private var userListResponse: Result<List<UserDto>> = Result.success(emptyList())
    private var userDetailResponse: Result<UserDetailDto> = Result.success(UserDetailDto(
        "",
        "",
        "",
        "",
        0,
        0
    ))

    fun setUsersResponse(response: Result<List<UserDto>>) {
        userListResponse = response
    }

    fun setUserDetailResponse(response: Result<UserDetailDto>) {
        userDetailResponse = response
    }

    override suspend fun getUsers(perPage: Int, since: Int): Result<List<UserDto>> {
        return userListResponse
    }

    override suspend fun getUserDetail(username: String): Result<UserDetailDto> {
        return userDetailResponse
    }
}