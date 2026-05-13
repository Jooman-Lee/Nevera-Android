package com.anddd.nevera.feature.alarmlist.alarmlist.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.anddd.nevera.core.designsystem.icon.NeveraIcons
import com.anddd.nevera.core.designsystem.ui.theme.NeveraTheme
import com.anddd.nevera.feature.alarmlist.alarmlist.model.AlarmItem
import com.anddd.nevera.feature.alarmlist.alarmlist.model.AlarmListUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AlarmListContent(
    state: AlarmListUiState,
    onBackClick: () -> Unit,
    onAlarmClick: (String) -> Unit,
    onEnableNotificationClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "알림",
                        style = NeveraTheme.typography.titleMedium,
                        color = NeveraTheme.colors.textPrimary,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            painter = NeveraIcons.ArrowBack,
                            contentDescription = "뒤로가기",
                            tint = NeveraTheme.colors.iconPrimary,
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = NeveraTheme.colors.backgroundPrimary,
                ),
            )
        },
        containerColor = NeveraTheme.colors.backgroundPrimary,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            if (!state.isNotificationPermissionGranted) {
                AlarmPermissionBanner(
                    onEnableClick = onEnableNotificationClick,
                    modifier = Modifier.padding(NeveraTheme.spacing.padding16),
                )
            }

            if (state.alarms.isEmpty()) {
                AlarmEmptyContent()
            } else {
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(items = state.alarms, key = { it.id }) { alarm ->
                        AlarmListItem(
                            item = alarm,
                            onClick = { onAlarmClick(alarm.id) },
                        )
                    }
                    item {
                        Text(
                            text = "최근 30일간의 알림을 확인할 수 있어요",
                            style = NeveraTheme.typography.captionMedium,
                            color = NeveraTheme.colors.textCaption,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = NeveraTheme.spacing.padding16,
                                    vertical = NeveraTheme.spacing.gap16,
                                ),
                        )
                    }
                }
            }
        }
    }
}

private val previewAlarms = listOf(
    AlarmItem(id = "1", title = "삼겹살(12,000)이 내일까지예요", body = "오늘 저녁은 [제육볶음] 어떠세요?", timeAgo = "59분 전", isRead = false),
    AlarmItem(id = "2", title = "삼겹살(12,000)이 내일까지예요", body = "오늘 저녁은 [제육볶음] 어떠세요?", timeAgo = "23시간 전", isRead = true),
    AlarmItem(id = "3", title = "삼겹살(12,000)이 내일까지예요", body = "오늘 저녁은 [제육볶음] 어떠세요?", timeAgo = "1일 전", isRead = true),
    AlarmItem(id = "4", title = "삼겹살(12,000)이 내일까지예요", body = "오늘 저녁은 [제육볶음] 어떠세요?", timeAgo = "30일 전", isRead = true),
)

@Preview(
    name = "Image1 - 알림 목록 (권한 ON)",
    showBackground = true,
    widthDp = 360
)
@Composable
private fun PreviewAlarmListWithItems() {
    NeveraTheme {
        AlarmListContent(
            state = AlarmListUiState(alarms = previewAlarms, isNotificationPermissionGranted = true),
            onBackClick = {},
            onAlarmClick = {},
            onEnableNotificationClick = {},
        )
    }
}

@Preview(
    name = "Image2 - 빈 목록 (권한 ON)",
    showBackground = true,
    widthDp = 360
)
@Composable
private fun PreviewAlarmListEmpty() {
    NeveraTheme {
        AlarmListContent(
            state = AlarmListUiState(alarms = emptyList(), isNotificationPermissionGranted = true),
            onBackClick = {},
            onAlarmClick = {},
            onEnableNotificationClick = {},
        )
    }
}

@Preview(
    name = "Image3 - 빈 목록 (권한 OFF)",
    showBackground = true,
    widthDp = 360
)
@Composable
private fun PreviewAlarmListEmptyPermissionOff() {
    NeveraTheme {
        AlarmListContent(
            state = AlarmListUiState(alarms = emptyList(), isNotificationPermissionGranted = false),
            onBackClick = {},
            onAlarmClick = {},
            onEnableNotificationClick = {},
        )
    }
}

@Preview(
    name = "Image4 - 알림 목록 (권한 OFF)",
    showBackground = true,
    widthDp = 360
)
@Composable
private fun PreviewAlarmListWithItemsPermissionOff() {
    NeveraTheme {
        AlarmListContent(
            state = AlarmListUiState(alarms = previewAlarms, isNotificationPermissionGranted = false),
            onBackClick = {},
            onAlarmClick = {},
            onEnableNotificationClick = {},
        )
    }
}

@Preview(
    name = "Image5 - 알림 목록 + 미읽음 (권한 OFF)",
    showBackground = true,
    widthDp = 360
)
@Composable
private fun PreviewAlarmListWithUnreadPermissionOff() {
    NeveraTheme {
        AlarmListContent(
            state = AlarmListUiState(
                alarms = previewAlarms.map { it.copy(isRead = it.id != "1") },
                isNotificationPermissionGranted = false,
            ),
            onBackClick = {},
            onAlarmClick = {},
            onEnableNotificationClick = {},
        )
    }
}
