package com.anddd.nevera.feature.alarmlist.alarmlist.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.anddd.nevera.feature.alarmlist.alarmlist.AlarmListScreen

const val ALARM_LIST_ROUTE = "alarm_list"

fun NavGraphBuilder.alarmListScreen(
    onNavigateBack: () -> Unit,
) {
    composable(route = ALARM_LIST_ROUTE) {
        AlarmListScreen(onNavigateBack = onNavigateBack)
    }
}
