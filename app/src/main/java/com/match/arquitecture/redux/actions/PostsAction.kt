package com.match.arquitecture.redux.actions

import com.match.arquitecture.models.Post

sealed interface PostsAction {
    data object RetrievePosts: PostsAction
    data class SuccessfullyRetrievePosts(var posts: List<Post>) : PostsAction
}