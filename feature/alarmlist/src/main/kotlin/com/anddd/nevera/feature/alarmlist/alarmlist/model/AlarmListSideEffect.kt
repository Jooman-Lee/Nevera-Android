package com.anddd.nevera.feature.alarmlist.alarmlist.model

import com.anddd.nevera.core.mvi.NeveraSideEffect

sealed interface AlarmListSideEffect : NeveraSideEffect {
    data object NavigateBack : AlarmListSideEffect
    data object NavigateToNotificationSettings : AlarmListSideEffect
}
