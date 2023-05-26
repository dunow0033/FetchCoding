package com.example.fetchcoding.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ListItem(
    val id: Int,
    val listId: Int,
    val name: String?
)