package com.example.fetchcoding.repository

import com.example.fetchcoding.remote.ListItemManager
import com.example.fetchcoding.utils.Resource
import kotlinx.coroutines.flow.flow

class ListItemRepository(private val listItemManager: ListItemManager) {

    fun getItems() = flow {
        val resource = try {
            val response = listItemManager.getItems()
            if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error(response.errorBody().toString())
            }
        } catch (ex: Exception) {
            Resource.Error(ex.toString())
        }
        emit(resource)
    }
}