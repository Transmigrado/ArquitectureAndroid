package com.match.arquitecture.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.match.arquitecture.models.Post

@Composable
fun PostListView(posts: List<Post>) {

    LazyVerticalGrid(
        columns = object : GridCells {
            override fun Density.calculateCrossAxisCellSizes(
                availableSize: Int,
                spacing: Int
            ): List<Int> {
                val firstColumn = (availableSize - spacing) * 2 / 3
                val secondColumn = availableSize - spacing - firstColumn
                return listOf(firstColumn, secondColumn)
            }
        },
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
        contentPadding = PaddingValues(6.dp),
        modifier = Modifier
            .fillMaxSize()
    ) {
        posts.forEachIndexed { index, item ->
            item(span = { GridItemSpan(1) }) {
                Box {
                    Text(text = item.message)
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ProfileHeaderPreview() {

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

    PostListView(posts)
}
