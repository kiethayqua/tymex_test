package vn.kietnguyendev.tymexhometest.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import vn.kietnguyendev.tymexhometest.domain.model.User

@Serializable
data class UserDto(
    val login: String,
    @SerialName("avatar_url")
    val avatarUrl: String,
    @SerialName("html_url")
    val htmlUrl: String
) {
    fun toUserModel(): User = User(
        username = login,
        avatarUrl = avatarUrl,
        pageUrl = htmlUrl
    )
}