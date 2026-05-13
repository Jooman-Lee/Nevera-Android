package com.anddd.nevera.feature.alarmlist.alarmlist.model

import com.anddd.nevera.core.mvi.NeveraIntent

sealed interface AlarmListIntent : NeveraIntent {
    data object LoadAlarms : AlarmListIntent
    data class ClickAlarmItem(val alarmId: String) : AlarmListIntent
    data object ClickBack : AlarmListIntent
    data object ClickEnableNotification : AlarmListIntent
}
