package com.sensorfields.chore.android.ui.chore.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewWrapper
import com.sensorfields.chore.resources.Res
import com.sensorfields.chore.resources.chore_create_name
import com.sensorfields.chore.theme.AppPreviewWrapper
import com.sensorfields.chore.theme.Text
import com.sensorfields.chore.theme.TextField
import org.jetbrains.compose.resources.stringResource

@Composable
fun ChoreCreateWhat(
    name: String,
    onNameChange: (String) -> Unit,
    onDoneClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusRequester = remember { FocusRequester() }
    Column(
        modifier = modifier,
    ) {
        TextField(
            value = name,
            onValueChange = onNameChange,
            modifier = Modifier
                .focusRequester(focusRequester)
                .fillMaxWidth(),
            label = { Text(stringResource(Res.string.chore_create_name)) },
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(
                onDone = { onDoneClick() },
            ),
            singleLine = true,
        )
    }
    SideEffect {
        focusRequester.requestFocus()
    }
}

@Preview(showBackground = true)
@PreviewWrapper(AppPreviewWrapper::class)
@Composable
private fun Preview() {
    Column {
        ChoreCreateWhat(
            name = "Chore name here",
            onNameChange = {},
            onDoneClick = {},
        )
    }
}
