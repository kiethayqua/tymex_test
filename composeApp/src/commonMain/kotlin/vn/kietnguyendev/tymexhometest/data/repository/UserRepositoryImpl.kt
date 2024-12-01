package vn.kietnguyendev.tymexhometest.data.repository

import vn.kietnguyendev.tymexhometest.data.network.IUserApi
import vn.kietnguyendev.tymexhometest.domain.model.User
import vn.kietnguyendev.tymexhometest.domain.model.UserDetail
import vn.kietnguyendev.tymexhometest.domain.repository.UserRepository

class UserRepositoryImpl(private val userApi: IUserApi): UserRepository {
    override suspend fun getUsers(perPage: Int, since: Int): List<User> {
        val getUsersResponse = userApi.getUsers(perPage, since)

        if (getUsersResponse.isSuccess) {
            return getUsersResponse.getOrNull()?.map { it.toUserModel() } ?: emptyList()
        }

        return emptyList()
    }

    override suspend fun getUserDetail(username: String): UserDetail? {
        val getUserDetailResponse = userApi.getUserDetail(username)

        if (getUserDetailResponse.isSuccess) {
            return getUserDetailResponse.getOrNull()?.toUserDetailModel()
        }

        return null
    }
}