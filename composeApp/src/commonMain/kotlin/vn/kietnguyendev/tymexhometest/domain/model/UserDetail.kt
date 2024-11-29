package vn.kietnguyendev.tymexhometest.domain.model

data class UserDetail(
    val username: String,
    val avatarUrl: String,
    val pageUrl: String,
    val location: String,
    val followers: Int,
    val following: Int
)