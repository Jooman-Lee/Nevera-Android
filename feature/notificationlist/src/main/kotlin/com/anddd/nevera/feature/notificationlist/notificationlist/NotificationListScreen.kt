package com.anddd.nevera.feature.notificationlist.notificationlist

import android.content.Intent
import android.provider.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.anddd.nevera.feature.notificationlist.notificationlist.component.NotificationListContent
import com.anddd.nevera.feature.notificationlist.notificationlist.model.NotificationListIntent
import com.anddd.nevera.feature.notificationlist.notificationlist.model.NotificationListSideEffect
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun NotificationListScreen(
    onNavigateBack: () -> Unit,
    viewModel: NotificationListViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val state = viewModel.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.handleIntent(NotificationListIntent.LoadNotifications)
    }

    viewModel.collectSideEffect { effect ->
        when (effect) {
            NotificationListSideEffect.NavigateBack -> onNavigateBack()

            NotificationListSideEffect.NavigateToNotificationSettings ->
                context.startActivity(
                    Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
                        putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
                    }
                )
        }
    }

    NotificationListContent(
        state = state,
        onBackClick = { viewModel.handleIntent(NotificationListIntent.ClickBack) },
        onNotificationClick = { id -> viewModel.handleIntent(NotificationListIntent.ClickNotificationItem(id)) },
        onEnableNotificationClick = { viewModel.handleIntent(NotificationListIntent.ClickEnableNotification) },
    )
}
