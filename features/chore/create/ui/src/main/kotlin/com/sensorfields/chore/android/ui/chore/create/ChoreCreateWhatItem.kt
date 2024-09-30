package com.sensorfields.chore.android.ui.chore.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.dp
import com.sensorfields.chore.android.ui.theme.AppTheme

@Composable
internal fun ColumnScope.ChoreCreateWhatItem(
    isExpanded: Boolean,
    name: String,
    onNameChange: (String) -> Unit,
    onDoneClick: () -> Unit,
) {
    if (isExpanded) {
        val focusRequester = remember { FocusRequester() }
        OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            modifier = Modifier
                .focusRequester(focusRequester)
                .fillMaxWidth(),
            label = { Text(stringResource(R.string.chore_create_name)) },
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { onDoneClick() }
            ),
            singleLine = true
        )
        SideEffect {
            focusRequester.requestFocus()
        }
    } else {
        Text(
            name,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            style = MaterialTheme.typography.titleMedium,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewExpanded() = AppTheme {
    Column {
        ChoreCreateWhatItem(
            isExpanded = true,
            name = "Chore name here",
            onNameChange = {},
            onDoneClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewCollapsed() = AppTheme {
    Column {
        ChoreCreateWhatItem(
            isExpanded = false,
            name = "Chore name here",
            onNameChange = {},
            onDoneClick = {}
        )
    }
}
