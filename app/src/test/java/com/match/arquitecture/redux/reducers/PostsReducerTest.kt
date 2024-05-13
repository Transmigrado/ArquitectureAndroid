package com.match.arquitecture.redux.reducers

import com.match.arquitecture.models.Post
import com.match.arquitecture.redux.actions.PostsAction
import org.junit.Assert
import org.junit.Test

class PostsReducerTest {

    val post = Post(
        uid = "123",
        message = "Lorem Ipsum ",
    )

    val post2 = Post(
        uid = "123",
        message = "Lorem Ipsum ",
    )

    val post3 = Post(
        uid = "123",
        message = "Lorem Ipsum ",
    )


    val list: List<Post> =  mutableListOf(post, post2, post3).toList()

    @Test
    fun `retrieve and load true`() {
        val state = PostsState()
        val newState = postsReducer(state, PostsAction.RetrievePosts)
        Assert.assertEquals(true, newState.isLoading)
    }

    @Test
    fun `retrieve posts successfully`() {

        val state = PostsState()
        val newState = postsReducer(state, PostsAction.SuccessfullyRetrievePosts(list))
        Assert.assertEquals(list, newState.posts)
        Assert.assertEquals(false, newState.isLoading)
    }
}
