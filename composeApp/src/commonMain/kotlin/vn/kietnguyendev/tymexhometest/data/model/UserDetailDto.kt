package vn.kietnguyendev.tymexhometest.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import vn.kietnguyendev.tymexhometest.domain.model.UserDetail

@Serializable
data class UserDetailDto(
    val login: String,
    @SerialName("avatar_url")
    val avatarUrl: String,
    @SerialName("html_url")
    val htmlUrl: String,
    val location: String,
    val followers: Int,
    val following: Int
) {
    fun toUserDetailModel() = UserDetail(
        username = login,
        avatarUrl = avatarUrl,
        pageUrl = htmlUrl,
        location = location,
        followers = followers,
        following = following
    )
}