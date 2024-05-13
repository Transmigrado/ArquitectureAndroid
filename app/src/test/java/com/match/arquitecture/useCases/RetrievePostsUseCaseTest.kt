package com.match.arquitecture.useCases

import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.match.arquitecture.models.Post
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class RetrievePostsUseCaseTest {

    private val dbMock = mockk<FirebaseFirestore>()
    private lateinit var retrievePostsUseCase: RetrievePostsUseCase
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

    private val posts: List<Post> =  mutableListOf(post, post2, post3).toList()

    @Before
    fun setUp() {
        mockkStatic(FirebaseFirestore::class)
        every { FirebaseFirestore.getInstance() } returns dbMock
        retrievePostsUseCase = RetrievePostsUseCase(dbMock)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }


    @Test
    fun `fetchAsyncData should return  list on successfully`()  {

        val taskMock = mockk<Task<QuerySnapshot>>()

        every { dbMock.collection(any()) } returns mockk()
        every { dbMock.collection(any()).get() } returns taskMock

        every { taskMock.addOnSuccessListener(any()) } answers {
            val onSuccessListener = arg<OnSuccessListener<QuerySnapshot>>(0)
            val documents = mockk<QuerySnapshot>()
            every { documents.isEmpty } returns true // Simulate an empty QuerySnapshot
            val iterator = mockk<MutableIterator<QueryDocumentSnapshot>>()
            every { iterator.hasNext() } returns false // Simulate empty iterator
            every { documents.iterator() } returns iterator
            onSuccessListener.onSuccess(documents)
            taskMock
        }

        every { taskMock.addOnFailureListener(any()) } answers {
            taskMock
        }

        val result = runBlocking {
            retrievePostsUseCase.fetchAsyncData()
        }

        assert(result.isEmpty())
    }

    @Test
    fun `fetchAsyncData should return empty list on failure`()  {

        val taskMock = mockk<Task<QuerySnapshot>>()

        every { dbMock.collection(any()) } returns mockk()
        every { dbMock.collection(any()).get() } returns taskMock

        every { taskMock.addOnSuccessListener(any()) } answers {
            taskMock
        }

        every { taskMock.addOnFailureListener(any()) } answers {
            val onFailureListener = arg<OnFailureListener>(0)
            val exception = Exception()
            onFailureListener.onFailure(exception)
            taskMock
        }

        val result = runBlocking {
            retrievePostsUseCase.fetchAsyncData()
        }

        assert(result.isEmpty())
    }
}