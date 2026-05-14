package com.anddd.nevera.feature.notificationlist.notificationlist

import com.anddd.nevera.core.mvi.NeveraViewModel
import com.anddd.nevera.feature.notificationlist.notificationlist.model.NotificationItem
import com.anddd.nevera.feature.notificationlist.notificationlist.model.NotificationListIntent
import com.anddd.nevera.feature.notificationlist.notificationlist.model.NotificationListMutation
import com.anddd.nevera.feature.notificationlist.notificationlist.model.NotificationListSideEffect
import com.anddd.nevera.feature.notificationlist.notificationlist.model.NotificationListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.Syntax
import javax.inject.Inject

@HiltViewModel
class NotificationListViewModel @Inject constructor(

) : NeveraViewModel<NotificationListUiState, NotificationListSideEffect, NotificationListIntent, NotificationListMutation>(
    NotificationListUiState()
) {

    override fun handleIntent(intent: NotificationListIntent) {
        when (intent) {
            NotificationListIntent.LoadNotifications -> loadNotifications()

            is NotificationListIntent.ClickNotificationItem -> markNotificationRead(intent.notificationId)

            NotificationListIntent.ClickBack -> navigateBack()

            NotificationListIntent.ClickEnableNotification -> navigateToNotificationSettings()
        }
    }

    override suspend fun Syntax<NotificationListUiState, NotificationListSideEffect>.applyMutation(
        mutation: NotificationListMutation
    ) {
        when (mutation) {
            NotificationListMutation.ShowLoading -> reduce { state.copy(isLoading = true) }

            is NotificationListMutation.UpdateNotifications -> reduce {
                state.copy(
                    isLoading = false,
                    notifications = mutation.notifications,
                    isNotificationPermissionGranted = mutation.isPermissionGranted,
                )
            }

            is NotificationListMutation.MarkNotificationRead -> reduce {
                state.copy(
                    notifications = state.notifications.map { item ->
                        if (item.id == mutation.notificationId) item.copy(isRead = true) else item
                    }
                )
            }
        }
    }

    private fun loadNotifications() = intent {
        applyMutation(NotificationListMutation.ShowLoading)
        // TODO: replace with actual UseCase injection
        applyMutation(
            NotificationListMutation.UpdateNotifications(
                notifications = mockNotifications(),
                isPermissionGranted = true,
            )
        )
    }

    private fun markNotificationRead(notificationId: String) = intent {
        applyMutation(NotificationListMutation.MarkNotificationRead(notificationId))
    }

    private fun navigateBack() = intent {
        postSideEffect(NotificationListSideEffect.NavigateBack)
    }

    private fun navigateToNotificationSettings() = intent {
        postSideEffect(NotificationListSideEffect.NavigateToNotificationSettings)
    }


    private fun mockNotifications() = listOf(
        NotificationItem(
            id = "1",
            title = "삼겹살(12,000)이 내일까지예요",
            body = "오늘 저녁은 [제육볶음] 어떠세요?",
            timeAgo = "59분 전",
            isRead = false,
        ),
        NotificationItem(
            id = "2",
            title = "삼겹살(12,000)이 내일까지예요",
            body = "오늘 저녁은 [제육볶음] 어떠세요?",
            timeAgo = "23시간 전",
            isRead = true,
        ),
        NotificationItem(
            id = "3",
            title = "삼겹살(12,000)이 내일까지예요",
            body = "오늘 저녁은 [제육볶음] 어떠세요?",
            timeAgo = "1일 전",
            isRead = true,
        ),
        NotificationItem(
            id = "4",
            title = "삼겹살(12,000)이 내일까지예요",
            body = "오늘 저녁은 [제육볶음] 어떠세요?",
            timeAgo = "30일 전",
            isRead = true,
        ),
    )
}
