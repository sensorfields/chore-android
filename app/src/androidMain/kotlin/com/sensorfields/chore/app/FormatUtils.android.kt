package com.sensorfields.chore.app

import androidx.compose.runtime.Composable
import com.sensorfields.chore.app.chore.create.ChoreCreateState
import com.sensorfields.chore.domain.models.Chore
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.Month
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.MonthNames
import org.jetbrains.compose.resources.stringResource

@Composable
public fun ChoreCreateState.Repeat.format(): String = stringResource(resource)

@Composable
public fun Chore.SortProperty.format(): String = stringResource(resource)

@Composable
public fun DayOfWeek.format(): String = dayOfWeekNames().names[ordinal]

@Composable
public fun Month.format(): String = monthNames().names[ordinal]

@Composable
private fun dayOfWeekNames(): DayOfWeekNames = DayOfWeekNames(DayOfWeek.entries.map { stringResource(it.resource) })

@Composable
private fun monthNames(): MonthNames = MonthNames(Month.entries.map { stringResource(it.resource) })
