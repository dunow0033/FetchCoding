package com.example.fetchcoding.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ListItems(
    val id: Int,
    val listId: Int,
    val name: String?
)