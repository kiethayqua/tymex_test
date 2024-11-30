package vn.kietnguyendev.tymexhometest.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import tymexhometest.composeapp.generated.resources.Res
import tymexhometest.composeapp.generated.resources.home_title
import vn.kietnguyendev.tymexhometest.presentation.component.CustomAppBar
import vn.kietnguyendev.tymexhometest.presentation.component.UserCard

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
    navigateToUserDetail: (String) -> Unit
) {
    val uiState = viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            CustomAppBar(
                title = { Text(stringResource(Res.string.home_title), fontWeight = FontWeight.Bold) }
            )
        }
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            if (uiState.value.isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {
                LazyColumn {
                    val users = uiState.value.data
                    itemsIndexed(users) { index, user ->
                        if (index == 0) Spacer(modifier = Modifier.height(8.dp))
                        UserCard(
                            avatarUrl = user.avatarUrl,
                            name = user.username,
                            pageUrl = user.pageUrl
                        ) {
                            navigateToUserDetail(user.username)
                        }
                        Spacer(modifier = Modifier.height(16.dp))

                        if (index == users.lastIndex) {
                            if (uiState.value.isLoadingMore) {
                                Box(
                                    modifier = Modifier.fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            } else {
                                viewModel.getUsers(isLoadMore = true)
                            }
                        }
                    }
                }
            }
        }
    }
}