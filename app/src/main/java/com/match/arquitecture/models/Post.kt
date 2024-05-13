package com.match.arquitecture.models

import com.google.firebase.firestore.PropertyName

data class Post(
    @get:PropertyName("uid")
    @set:PropertyName("uid")
    var uid: String = "",
    @get:PropertyName("message")
    @set:PropertyName("message")
    var message: String = "",
)