package vn.kietnguyendev.tymexhometest.presentation.user_detail

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import kotlinx.serialization.Serializable

@Serializable
data class UserDetailRoute(val username: String)

fun NavController.navigateToUserDetail(username: String, navOptions: NavOptions? = null) {
    navigate(UserDetailRoute(username), navOptions)
}
