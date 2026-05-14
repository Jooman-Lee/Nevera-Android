package com.anddd.nevera.feature.notificationlist.notificationlist.model

import com.anddd.nevera.core.mvi.NeveraIntent

sealed interface NotificationListIntent : NeveraIntent {
    data object LoadNotifications : NotificationListIntent
    data class ClickNotificationItem(val notificationId: String) : NotificationListIntent
    data object ClickBack : NotificationListIntent
    data object ClickEnableNotification : NotificationListIntent
}
