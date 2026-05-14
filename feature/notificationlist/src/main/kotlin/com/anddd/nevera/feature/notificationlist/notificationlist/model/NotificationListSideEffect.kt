package com.anddd.nevera.feature.notificationlist.notificationlist.model

import com.anddd.nevera.core.mvi.NeveraSideEffect

sealed interface NotificationListSideEffect : NeveraSideEffect {
    data object NavigateBack : NotificationListSideEffect
    data object NavigateToNotificationSettings : NotificationListSideEffect
}
