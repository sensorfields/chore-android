package com.sensorfields.chore.app

import androidx.compose.runtime.Composable
import com.sensorfields.chore.app.chore.create.ChoreCreateState
import org.jetbrains.compose.resources.stringResource

@Composable
public fun ChoreCreateState.When.Repeat.format(): String = stringResource(resource)
