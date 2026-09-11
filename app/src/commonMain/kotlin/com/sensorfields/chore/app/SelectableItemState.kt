package com.sensorfields.chore.app

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlin.enums.enumEntries

public data class SelectableItemState<T>(
    val value: T,
    val selected: Boolean,
)

public inline fun <reified T : Enum<T>> generateSelectableItemState(
    selected: Set<T> = emptySet(),
): ImmutableList<SelectableItemState<T>> = enumEntries<T>().map { value ->
    SelectableItemState(
        value = value,
        selected = selected.contains(value),
    )
}.toImmutableList()

public fun generateSelectableItemState(
    range: IntRange,
    selected: Set<Int> = emptySet(),
): ImmutableList<SelectableItemState<Int>> = range.map { value ->
    SelectableItemState(
        value = value,
        selected = selected.contains(value),
    )
}.toImmutableList()

public fun <T> List<SelectableItemState<T>>.selected(): Set<T> = filter { it.selected }.map { it.value }.toSet()
