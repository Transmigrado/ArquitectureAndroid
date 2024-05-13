package com.match.arquitecture.useCases

import com.google.firebase.firestore.FirebaseFirestore
import com.match.arquitecture.models.Collections
import com.match.arquitecture.models.Post
import com.match.arquitecture.useCases.interfaces.RetrievePostsUseCaseInterface

import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class RetrievePostsUseCase(private val db: FirebaseFirestore): RetrievePostsUseCaseInterface {

    override suspend fun fetchAsyncData(): List<Post> =

        suspendCoroutine { cont ->
            db.collection(Collections.POSTS)
                .get()
                .addOnSuccessListener { documents ->
                    val posts = documents.map {  it.toObject(Post::class.java) }
                    cont.resume(posts)
                }
                .addOnFailureListener { exception ->
                    val emptyPosts: List<Post> = emptyList()
                    cont.resume(emptyPosts)
                }
        }


}