package com.anddd.nevera.feature.alarmlist.alarmlist.model

import com.anddd.nevera.core.mvi.NeveraMutation

sealed interface AlarmListMutation : NeveraMutation {
    data object ShowLoading : AlarmListMutation
    data class UpdateAlarms(
        val alarms: List<AlarmItem>,
        val isPermissionGranted: Boolean,
    ) : AlarmListMutation
    data class MarkAlarmRead(val alarmId: String) : AlarmListMutation
}
