package com.example.fetchcoding.remote

import com.example.fetchcoding.model.ListItems
import retrofit2.Response
import retrofit2.http.GET

interface TodoService {

    @GET("/hiring.json")
    suspend fun getTodos(): Response<List<ListItems>>

}