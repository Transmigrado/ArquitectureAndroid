package com.match.arquitecture.redux.reducers

import com.match.arquitecture.models.Post
import com.match.arquitecture.redux.actions.PostsAction
import org.reduxkotlin.Reducer
import org.reduxkotlin.typedReducer

data class PostsState (
    val isLoading: Boolean = false,
    val posts: List<Post> = emptyList(),
)

val postsReducer: Reducer<PostsState> = typedReducer<PostsState, PostsAction> { state, action ->
    when (action) {
        is PostsAction.SuccessfullyRetrievePosts -> state.copy(isLoading = false, posts = action.posts)
        is PostsAction.RetrievePosts -> state.copy(isLoading = true)
    }
}