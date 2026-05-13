package com.anddd.nevera.feature.alarmlist.alarmlist.model

import com.anddd.nevera.core.mvi.NeveraState

data class AlarmListUiState(
    val isLoading: Boolean = false,
    val isNotificationPermissionGranted: Boolean = true,
    val alarms: List<AlarmItem> = emptyList(),
) : NeveraState

data class AlarmItem(
    val id: String,
    val title: String,
    val body: String,
    val timeAgo: String,
    val isRead: Boolean,
)
