package com.match.arquitecture.redux.reducers

import com.match.arquitecture.utils.DummyAction
import org.junit.Assert
import org.junit.Test

class RootReducerTest {
    @Test
    fun `empty state root`() {
        val state = AppState()
        val newState = rootReducer(state, DummyAction.EmptyAction)
        Assert.assertEquals(newState, state)
    }
}