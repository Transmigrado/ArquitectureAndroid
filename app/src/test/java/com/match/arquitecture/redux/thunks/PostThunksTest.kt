package com.match.arquitecture.redux.thunks

import com.match.arquitecture.models.Post
import com.match.arquitecture.redux.actions.PostsAction
import com.match.arquitecture.useCases.interfaces.RetrievePostsUseCaseInterface
import com.match.arquitecture.utils.ReduxStore
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class PostThunksTest {

    private val store = mockk<ReduxStore>()

    val post = Post(
       uid = "123",
        message = "Lorem Ipsum"
    )

    val post2 = Post(
        uid = "123",
        message = "Lorem Ipsum"
    )

    val post3 = Post(
        uid = "123",
        message = "Lorem Ipsum"
    )

    val posts: List<Post> =  mutableListOf(post, post2, post3).toList()

    @Before
    fun initConfig(){

        every { store.dispatch } returns mockk()
        every { store.getState } returns mockk()
        every { store.dispatch(any()) } returns Unit

    }

    @Test
    fun `it call retrieve thunk and call dispatch SuccessfullyRetrievePosts action with posts`() {

        val useCase = mockk<RetrievePostsUseCaseInterface>()
        coEvery{ useCase.fetchAsyncData() } returns posts

        val thunk = PostThunks(useCase)
        thunk.retrieveThunk()(store.dispatch, store.getState, null)
        verify { store.dispatch(PostsAction.SuccessfullyRetrievePosts(posts)) }
    }


}