package com.data.model

data class Post(
    val views_count: String,
    val likes_count: String,
    val comments_count: String,
    val bookmarks_count: String,
    val reposts_count: String,
    val author: Author
)

data class Author(
    val avatar_image: AvatarPostImage
)
data class AvatarPostImage(
    val mentioned_users_count: String
)
