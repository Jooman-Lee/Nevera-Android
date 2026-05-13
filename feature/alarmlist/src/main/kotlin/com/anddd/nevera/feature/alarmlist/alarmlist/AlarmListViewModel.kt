package com.anddd.nevera.feature.alarmlist.alarmlist

import com.anddd.nevera.core.mvi.NeveraViewModel
import com.anddd.nevera.feature.alarmlist.alarmlist.model.AlarmItem
import com.anddd.nevera.feature.alarmlist.alarmlist.model.AlarmListIntent
import com.anddd.nevera.feature.alarmlist.alarmlist.model.AlarmListMutation
import com.anddd.nevera.feature.alarmlist.alarmlist.model.AlarmListSideEffect
import com.anddd.nevera.feature.alarmlist.alarmlist.model.AlarmListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.Syntax
import javax.inject.Inject

@HiltViewModel
class AlarmListViewModel @Inject constructor(

) : NeveraViewModel<AlarmListUiState, AlarmListSideEffect, AlarmListIntent, AlarmListMutation>(
    AlarmListUiState()
) {

    override fun onIntent(intent: AlarmListIntent) {
        when (intent) {
            AlarmListIntent.LoadAlarms -> loadAlarms()

            is AlarmListIntent.ClickAlarmItem -> markAlarmRead(intent.alarmId)

            AlarmListIntent.ClickBack -> navigateBack()

            AlarmListIntent.ClickEnableNotification -> navigateToNotificationSettings()
        }
    }

    override suspend fun Syntax<AlarmListUiState, AlarmListSideEffect>.onReduce(
        mutation: AlarmListMutation
    ) {
        when (mutation) {
            AlarmListMutation.ShowLoading -> reduce { state.copy(isLoading = true) }

            is AlarmListMutation.UpdateAlarms -> reduce {
                state.copy(
                    isLoading = false,
                    alarms = mutation.alarms,
                    isNotificationPermissionGranted = mutation.isPermissionGranted,
                )
            }

            is AlarmListMutation.MarkAlarmRead -> reduce {
                state.copy(
                    alarms = state.alarms.map { item ->
                        if (item.id == mutation.alarmId) item.copy(isRead = true) else item
                    }
                )
            }
        }
    }

    private fun loadAlarms() = intent {
        onReduce(AlarmListMutation.ShowLoading)
        // TODO: replace with actual UseCase injection
        onReduce(
            AlarmListMutation.UpdateAlarms(
                alarms = mockAlarms(),
                isPermissionGranted = true,
            )
        )
    }

    private fun markAlarmRead(alarmId: String) = intent {
        onReduce(AlarmListMutation.MarkAlarmRead(alarmId))
    }

    private fun navigateBack() = intent {
        postSideEffect(AlarmListSideEffect.NavigateBack)
    }

    private fun navigateToNotificationSettings() = intent {
        postSideEffect(AlarmListSideEffect.NavigateToNotificationSettings)
    }


    private fun mockAlarms() = listOf(
        AlarmItem(
            id = "1",
            title = "삼겹살(12,000)이 내일까지예요",
            body = "오늘 저녁은 [제육볶음] 어떠세요?",
            timeAgo = "59분 전",
            isRead = false,
        ),
        AlarmItem(
            id = "2",
            title = "삼겹살(12,000)이 내일까지예요",
            body = "오늘 저녁은 [제육볶음] 어떠세요?",
            timeAgo = "23시간 전",
            isRead = true,
        ),
        AlarmItem(
            id = "3",
            title = "삼겹살(12,000)이 내일까지예요",
            body = "오늘 저녁은 [제육볶음] 어떠세요?",
            timeAgo = "1일 전",
            isRead = true,
        ),
        AlarmItem(
            id = "4",
            title = "삼겹살(12,000)이 내일까지예요",
            body = "오늘 저녁은 [제육볶음] 어떠세요?",
            timeAgo = "30일 전",
            isRead = true,
        ),
    )
}
