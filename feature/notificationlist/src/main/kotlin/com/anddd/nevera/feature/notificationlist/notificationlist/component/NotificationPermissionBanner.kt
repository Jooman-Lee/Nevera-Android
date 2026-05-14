package com.anddd.nevera.feature.notificationlist.notificationlist.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.anddd.nevera.core.designsystem.component.button.NeveraButtonColor
import com.anddd.nevera.core.designsystem.component.button.NeveraButtonSize
import com.anddd.nevera.core.designsystem.component.button.NeveraFilledButton
import com.anddd.nevera.core.designsystem.ui.theme.NeveraTheme

@Composable
internal fun NotificationPermissionBanner(
    onEnableClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(NeveraTheme.radius.medium),
        color = NeveraTheme.colors.surfaceSecondary,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(NeveraTheme.spacing.padding16),
            horizontalArrangement = Arrangement.spacedBy(NeveraTheme.spacing.gap12),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "기기의 앱 알림이 꺼져있어요",
                    style = NeveraTheme.typography.titleSmall,
                    color = NeveraTheme.colors.textTertiary,
                )
                Spacer(modifier = Modifier.height(NeveraTheme.spacing.gap2))
                Text(
                    text = "설정에서 권한을 허용해주세요",
                    style = NeveraTheme.typography.captionLarge,
                    color = NeveraTheme.colors.textQuaternary,
                )
            }
            NeveraFilledButton(
                label = "알람 켜기",
                onClick = onEnableClick,
                color = NeveraButtonColor.Secondary,
                size = NeveraButtonSize.XSmall,
                shape = RoundedCornerShape(NeveraTheme.radius.max)
            )
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 360
)
@Composable
private fun NotificationPermissionBannerPreview() {
    NeveraTheme {
        NotificationPermissionBanner(
            onEnableClick = {},
            modifier = Modifier.padding(NeveraTheme.spacing.padding16),
        )
    }
}
