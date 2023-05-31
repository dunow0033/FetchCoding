package com.example.fetchcoding.remote

class ListItemManager {

    private val listItemService: ListItemService
    private var retrofit = RetrofitInstance.providesRetrofit()

    init {
        listItemService = retrofit.create(ListItemService::class.java)
    }

    suspend fun getItems() =
        listItemService.getItems()
}