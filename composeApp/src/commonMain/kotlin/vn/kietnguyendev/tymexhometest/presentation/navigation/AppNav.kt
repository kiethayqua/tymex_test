package vn.kietnguyendev.tymexhometest.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import vn.kietnguyendev.tymexhometest.presentation.component.CustomAppBar
import vn.kietnguyendev.tymexhometest.presentation.home.HomeRoute
import vn.kietnguyendev.tymexhometest.presentation.home.HomeScreen
import vn.kietnguyendev.tymexhometest.presentation.user_detail.UserDetailRoute
import vn.kietnguyendev.tymexhometest.presentation.user_detail.UserDetailScreen
import vn.kietnguyendev.tymexhometest.presentation.user_detail.navigateToUserDetail
import kotlin.reflect.KClass

@Composable
fun AppBar(
    title: StringResource,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {

}

@Composable
fun AppNav(navController: NavHostController = rememberNavController()) {
    Scaffold{ innerPadding ->
        NavHost(
            navController = navController,
            startDestination = HomeRoute,
            modifier = Modifier.fillMaxSize().padding(innerPadding)
        ) {
            composable<HomeRoute> {
                HomeScreen { username -> navController.navigateToUserDetail(username) }
            }

            composable<UserDetailRoute> { navBackStack ->
                val userDetailRoute: UserDetailRoute = navBackStack.toRoute()

                UserDetailScreen(
                    username = userDetailRoute.username,
                    navigateUp = { navController.navigateUp() }
                )
            }
        }
    }
}