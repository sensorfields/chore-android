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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import com.sensorfields.chore.android.R
import com.sensorfields.chore.android.ui.theme.AppTheme
import com.sensorfields.chore.android.ui.theme.Text
import com.sensorfields.chore.android.ui.theme.TextField

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
            label = { Text(stringResource(R.string.chore_create_name)) },
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
@Composable
private fun Preview() = AppTheme {
    Column {
        ChoreCreateWhat(
            name = "Chore name here",
            onNameChange = {},
            onDoneClick = {},
        )
    }
}
