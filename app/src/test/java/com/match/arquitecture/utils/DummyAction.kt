package com.match.arquitecture.utils

sealed interface DummyAction {

    data object EmptyAction: DummyAction
}