package com.match.arquitecture.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import com.google.firebase.firestore.FirebaseFirestore
import com.match.arquitecture.models.Post
import com.match.arquitecture.redux.reducers.AppState
import com.match.arquitecture.redux.thunks.PostThunks
import com.match.arquitecture.ui.components.LoadingView
import com.match.arquitecture.ui.components.PostListView
import com.match.arquitecture.useCases.RetrievePostsUseCase
import org.reduxkotlin.compose.rememberDispatcher
import org.reduxkotlin.compose.selectState

@Composable
fun MainScreen() {

    val postsThunks = PostThunks(RetrievePostsUseCase(FirebaseFirestore.getInstance()))

    val posts by selectState<AppState, List<Post>> { postsState.posts }
    val isLoading by selectState<AppState, Boolean> { postsState.isLoading }

    val dispatch = rememberDispatcher()

    LaunchedEffect(Unit) {
        dispatch(postsThunks.retrieveThunk())
    }

    if(isLoading){
        LoadingView()
    } else {
        PostListView(posts = posts)
    }

}