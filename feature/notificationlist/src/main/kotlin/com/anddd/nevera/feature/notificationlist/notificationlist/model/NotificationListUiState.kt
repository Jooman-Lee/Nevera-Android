package com.anddd.nevera.feature.notificationlist.notificationlist.model

import com.anddd.nevera.core.mvi.NeveraState

data class NotificationListUiState(
    val isLoading: Boolean = false,
    val isNotificationPermissionGranted: Boolean = true,
    val notifications: List<NotificationItem> = emptyList(),
) : NeveraState

data class NotificationItem(
    val id: String,
    val title: String,
    val body: String,
    val timeAgo: String,
    val isRead: Boolean,
)
