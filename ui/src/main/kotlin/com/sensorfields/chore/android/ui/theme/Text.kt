package com.sensorfields.chore.android.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

@Composable
public fun Text(
    text: String,
    modifier: Modifier = Modifier,
    variantColor: Boolean = false,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
) {
    androidx.compose.material3.Text(
        text = text,
        modifier = modifier,
        color = if (variantColor) MaterialTheme.colorScheme.onSurfaceVariant else Color.Unspecified,
        textAlign = textAlign,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        onTextLayout = onTextLayout,
    )
}

@Composable
public fun BodyMediumText(
    text: String,
    modifier: Modifier = Modifier,
    variantColor: Boolean = false,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
) {
    androidx.compose.material3.Text(
        text = text,
        modifier = modifier,
        color = if (variantColor) MaterialTheme.colorScheme.onSurfaceVariant else Color.Unspecified,
        textAlign = textAlign,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        onTextLayout = onTextLayout,
        style = MaterialTheme.typography.bodyMedium,
    )
}

@Composable
public fun TitleMediumText(
    text: String,
    modifier: Modifier = Modifier,
    variantColor: Boolean = false,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
) {
    androidx.compose.material3.Text(
        text = text,
        modifier = modifier,
        color = if (variantColor) MaterialTheme.colorScheme.onSurfaceVariant else Color.Unspecified,
        textAlign = textAlign,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        onTextLayout = onTextLayout,
        style = MaterialTheme.typography.titleMedium,
    )
}
