package com.anddd.nevera.feature.notificationlist.notificationlist.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.anddd.nevera.feature.notificationlist.notificationlist.NotificationListScreen

const val NOTIFICATION_LIST_ROUTE = "notification_list"

fun NavGraphBuilder.notificationListScreen(
    onNavigateBack: () -> Unit,
) {
    composable(route = NOTIFICATION_LIST_ROUTE) {
        NotificationListScreen(onNavigateBack = onNavigateBack)
    }
}
