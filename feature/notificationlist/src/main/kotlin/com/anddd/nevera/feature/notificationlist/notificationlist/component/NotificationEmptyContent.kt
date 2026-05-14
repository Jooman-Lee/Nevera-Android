package com.anddd.nevera.feature.notificationlist.notificationlist.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anddd.nevera.core.designsystem.icon.NeveraIcons
import com.anddd.nevera.core.designsystem.ui.theme.NeveraTheme

@Composable
internal fun NotificationEmptyContent(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(432.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            painter = NeveraIcons.EmptyStateWarning,
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(64.dp),
        )
        Spacer(modifier = Modifier.height(NeveraTheme.spacing.gap16))
        Text(
            text = "아직 받은 알림이 없어요",
            style = NeveraTheme.typography.bodySmall,
            color = NeveraTheme.colors.textQuaternary,
        )
    }
}

@Preview(
    showBackground = true,
    widthDp = 360
)
@Composable
private fun NotificationEmptyContentPreview() {
    NeveraTheme {
        NotificationEmptyContent()
    }
}
