package com.match.arquitecture.redux.thunks.interfaces

import com.match.arquitecture.redux.reducers.AppState
import org.reduxkotlin.thunk.Thunk

interface PostThunksInterface {
    fun retrieveThunk(): Thunk<AppState>
}