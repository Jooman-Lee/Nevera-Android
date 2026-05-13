package com.anddd.nevera.feature.alarmlist.alarmlist.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anddd.nevera.core.designsystem.ui.theme.NeveraTheme
import com.anddd.nevera.feature.alarmlist.R
import com.anddd.nevera.feature.alarmlist.alarmlist.model.AlarmItem

@Composable
internal fun AlarmListItem(
    item: AlarmItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val backgroundColor = if (item.isRead) {
        NeveraTheme.colors.backgroundPrimary
    } else {
        NeveraTheme.colors.surfaceBrandPrimary
    }

    Column(modifier = modifier.background(backgroundColor)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundColor)
                .clickable(onClick = onClick)
                .padding(NeveraTheme.spacing.padding16),
            horizontalArrangement = Arrangement.spacedBy(NeveraTheme.spacing.gap12),
        ) {
            Box {
               Icon(
                   modifier = Modifier.size(NeveraTheme.iconSize.medium),
                   painter = painterResource(R.drawable.ic_listcell_expirationdate),
                   contentDescription = null,
                   tint = Color.Unspecified,
               )
            }

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        text = "유통기한",
                        style = NeveraTheme.typography.captionLarge,
                        color = NeveraTheme.colors.textQuaternary,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = item.timeAgo,
                        style = NeveraTheme.typography.captionMedium,
                        color = NeveraTheme.colors.textCaption,
                    )
                }
                Spacer(modifier = Modifier.height(NeveraTheme.spacing.gap4))
                Text(
                    text = item.title,
                    style = NeveraTheme.typography.bodyMedium,
                    color = NeveraTheme.colors.textSecondary,
                )
                Spacer(modifier = Modifier.height(NeveraTheme.spacing.gap2))
                Text(
                    text = item.body,
                    style = NeveraTheme.typography.captionLarge,
                    color = NeveraTheme.colors.textQuaternary,
                )
            }
        }
        HorizontalDivider(color = NeveraTheme.colors.dividerNormal)
    }
}

@Preview(showBackground = true)
@Composable
private fun AlarmListItemUnreadPreview() {
    NeveraTheme {
        AlarmListItem(
            item = AlarmItem(
                id = "1",
                title = "삼겹살(12,000)이 내일까지예요",
                body = "오늘 저녁은 [제육볶음] 어떠세요?",
                timeAgo = "59분 전",
                isRead = false,
            ),
            onClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AlarmListItemReadPreview() {
    NeveraTheme {
        AlarmListItem(
            item = AlarmItem(
                id = "2",
                title = "삼겹살(12,000)이 내일까지예요",
                body = "오늘 저녁은 [제육볶음] 어떠세요?",
                timeAgo = "1일 전",
                isRead = true,
            ),
            onClick = {},
        )
    }
}
