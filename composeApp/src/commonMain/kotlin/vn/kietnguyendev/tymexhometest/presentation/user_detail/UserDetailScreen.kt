package vn.kietnguyendev.tymexhometest.presentation.user_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import tymexhometest.composeapp.generated.resources.Res
import tymexhometest.composeapp.generated.resources.blog
import tymexhometest.composeapp.generated.resources.follower
import tymexhometest.composeapp.generated.resources.following
import tymexhometest.composeapp.generated.resources.user_detail_title
import vn.kietnguyendev.tymexhometest.presentation.component.CustomAppBar
import vn.kietnguyendev.tymexhometest.presentation.component.UserCard

@Composable
fun UserDetailScreen(
    username: String,
    viewModel: UserDetailViewModel = koinViewModel(),
    navigateUp: () -> Unit
) {
    val uiState = viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getUserDetail(username)
    }

    Scaffold(
        topBar = {
            CustomAppBar(
                title = { Text(stringResource(Res.string.user_detail_title), fontWeight = FontWeight.Bold) },
                startIcon = {
                    IconButton(onClick = navigateUp) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) {
        if (uiState.value.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                uiState.value.data?.let { user ->
                    Spacer(modifier = Modifier.height(8.dp))
                    UserCard(
                        avatarUrl = user.avatarUrl,
                        name = user.username.capitalize(),
                        location = user.location
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Box(modifier = Modifier.size(48.dp).clip(CircleShape).background(Color.LightGray), contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Filled.Person,
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text("${user.followers}+", fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                            Text(stringResource(Res.string.follower), fontSize = 14.sp)
                        }

                        Spacer(modifier = Modifier.width(60.dp))

                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Box(modifier = Modifier.size(48.dp).clip(CircleShape).background(Color.LightGray), contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Filled.Person,
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text("${user.following}+", fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                            Text(stringResource(Res.string.following), fontSize = 14.sp)
                        }
                    }
                    Spacer(modifier = Modifier.height(32.dp))
                    Text(stringResource(Res.string.blog), fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(user.pageUrl, fontSize = 14.sp)
                }
            }
        }
    }

}