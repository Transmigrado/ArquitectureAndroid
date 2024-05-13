package com.match.arquitecture.redux.reducers

import org.reduxkotlin.Reducer
import org.reduxkotlin.typedReducer

data class AppState(
    val postsState: PostsState = PostsState(),
)

val rootReducer: Reducer<AppState> = typedReducer<AppState, Any> { state, action ->
    AppState(
        postsState = postsReducer(state.postsState, action),
    )
}