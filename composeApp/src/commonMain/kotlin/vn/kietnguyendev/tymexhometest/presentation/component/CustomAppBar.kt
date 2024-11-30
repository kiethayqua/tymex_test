package vn.kietnguyendev.tymexhometest.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomAppBar(
    title: @Composable () -> Unit,
    startIcon: @Composable (() -> Unit)? = null,
    endIcon: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(Color.White)
    ) {
        if (startIcon != null) {
            Box(modifier = Modifier.align(alignment = Alignment.CenterStart)) {
                startIcon()
            }
        }

        Box(modifier = Modifier.align(Alignment.Center)) {
            title()
        }

        if (endIcon != null) {
            Box(modifier = Modifier.align(alignment = Alignment.CenterEnd)) {
                endIcon()
            }
        }
    }
}