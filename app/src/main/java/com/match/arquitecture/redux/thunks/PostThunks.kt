package com.match.arquitecture.redux.thunks

import com.match.arquitecture.redux.actions.PostsAction
import com.match.arquitecture.redux.reducers.AppState
import com.match.arquitecture.redux.thunks.interfaces.PostThunksInterface
import com.match.arquitecture.useCases.interfaces.RetrievePostsUseCaseInterface
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.reduxkotlin.thunk.Thunk

class PostThunks(private val useCase: RetrievePostsUseCaseInterface): PostThunksInterface {

    @OptIn(DelicateCoroutinesApi::class)
    override fun retrieveThunk(): Thunk<AppState> = { dispatch, _, _ ->

        dispatch(PostsAction.RetrievePosts)

        GlobalScope.launch {
            val posts =  useCase.fetchAsyncData()
            dispatch(PostsAction.SuccessfullyRetrievePosts(posts))
        }

    }


}