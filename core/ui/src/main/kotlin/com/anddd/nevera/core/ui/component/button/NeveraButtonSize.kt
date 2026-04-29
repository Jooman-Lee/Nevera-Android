package com.anddd.nevera.core.ui.component.button

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.anddd.nevera.core.designsystem.ui.theme.spacing.NeveraSpacing
import com.anddd.nevera.core.designsystem.ui.theme.typography.NeveraTypography

enum class NeveraButtonSize { XSmall, Small, Medium, Large }

internal data class NeveraButtonSizeSpec(
    val height: Dp,
    val horizontalPadding: Dp,
    val verticalPadding: Dp,
    val iconSize: Dp,
    val iconTextPadding: Dp,
    val textStyle: TextStyle,
)

internal fun NeveraButtonSize.toSpec(typography: NeveraTypography): NeveraButtonSizeSpec {

    return when (this) {
        NeveraButtonSize.Large -> NeveraButtonSizeSpec(
            height = 48.dp,
            horizontalPadding = NeveraSpacing.padding6,
            verticalPadding = NeveraSpacing.padding5,
            iconSize = 20.dp,
            iconTextPadding = 8.dp,
            textStyle = typography.titleMedium,
        )
        NeveraButtonSize.Medium -> NeveraButtonSizeSpec(
            height = 40.dp,
            horizontalPadding = 14.dp,
            verticalPadding = 10.dp,
            iconSize = 20.dp,
            iconTextPadding = 8.dp,
            textStyle = typography.titleSmall,
        )
        NeveraButtonSize.Small -> NeveraButtonSizeSpec(
            height = 34.dp,
            horizontalPadding = 12.dp,
            verticalPadding = 8.dp,
            iconSize = 16.dp,
            iconTextPadding = 6.dp,
            textStyle = typography.titleXSmall,
        )
        NeveraButtonSize.XSmall -> NeveraButtonSizeSpec(
            height = 28.dp,
            horizontalPadding = 8.dp,
            verticalPadding = 6.dp,
            iconSize = 12.dp,
            iconTextPadding = 4.dp,
            textStyle = typography.captionMedium,
        )
    }
}
