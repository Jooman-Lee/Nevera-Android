package com.anddd.nevera.feature.alarmlist.alarmlist

import android.content.Intent
import android.provider.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.anddd.nevera.feature.alarmlist.alarmlist.component.AlarmListContent
import com.anddd.nevera.feature.alarmlist.alarmlist.model.AlarmListIntent
import com.anddd.nevera.feature.alarmlist.alarmlist.model.AlarmListSideEffect
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun AlarmListScreen(
    onNavigateBack: () -> Unit,
    viewModel: AlarmListViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val state = viewModel.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.onIntent(AlarmListIntent.LoadAlarms)
    }

    viewModel.collectSideEffect { effect ->
        when (effect) {
            AlarmListSideEffect.NavigateBack -> onNavigateBack()

            AlarmListSideEffect.NavigateToNotificationSettings ->
                context.startActivity(
                    Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
                        putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
                    }
                )
        }
    }

    AlarmListContent(
        state = state,
        onBackClick = { viewModel.onIntent(AlarmListIntent.ClickBack) },
        onAlarmClick = { id -> viewModel.onIntent(AlarmListIntent.ClickAlarmItem(id)) },
        onEnableNotificationClick = { viewModel.onIntent(AlarmListIntent.ClickEnableNotification) },
    )
}
