package com.example.fetchcoding.remote

import com.example.fetchcoding.model.ListItem
import retrofit2.Response
import retrofit2.http.GET

interface ListItemService {

    @GET("/hiring.json")
    suspend fun getItems(): Response<List<ListItem>>

}