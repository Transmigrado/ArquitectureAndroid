package com.match.arquitecture.useCases.interfaces

import com.match.arquitecture.models.Post

interface RetrievePostsUseCaseInterface {

    suspend fun fetchAsyncData(): List<Post>
}