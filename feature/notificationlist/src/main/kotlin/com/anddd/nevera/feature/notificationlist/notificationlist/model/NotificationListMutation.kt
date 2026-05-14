package com.anddd.nevera.feature.notificationlist.notificationlist.model

import com.anddd.nevera.core.mvi.NeveraMutation

sealed interface NotificationListMutation : NeveraMutation {
    data object ShowLoading : NotificationListMutation
    data class UpdateNotifications(
        val notifications: List<NotificationItem>,
        val isPermissionGranted: Boolean,
    ) : NotificationListMutation
    data class MarkNotificationRead(val notificationId: String) : NotificationListMutation
}
