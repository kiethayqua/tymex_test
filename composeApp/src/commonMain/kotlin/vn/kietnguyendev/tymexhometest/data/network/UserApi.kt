package vn.kietnguyendev.tymexhometest.data.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import vn.kietnguyendev.tymexhometest.data.model.UserDetailDto
import vn.kietnguyendev.tymexhometest.data.model.UserDto
import vn.kietnguyendev.tymexhometest.util.Constants

class UserApi(
    private val client: HttpClient
) {
    suspend fun getUsers(perPage: Int = 20, since: Int = 0): Result<List<UserDto>> = runCatching {
        return@runCatching client.get("${Constants.BASE_URL}users?per_page=${perPage}&since=${since}").body()
    }

    suspend fun getUserDetail(username: String): Result<UserDetailDto> = runCatching {
        return@runCatching client.get("${Constants.BASE_URL}users/${username}").body()
    }
}