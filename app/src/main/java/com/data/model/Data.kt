package com.data.model

data class Data(
    val nickname: String,
    val avatar_image: AvatarImage
)

data class AvatarImage(
    val url_medium: String
)