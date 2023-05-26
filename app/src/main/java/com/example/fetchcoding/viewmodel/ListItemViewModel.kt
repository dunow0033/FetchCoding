package com.example.fetchcoding.viewmodel

import androidx.lifecycle.*
import com.example.fetchcoding.model.ListItem
import com.example.fetchcoding.repository.ListItemRepository
import com.example.fetchcoding.utils.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ListItemViewModel(val repo: ListItemRepository) : ViewModel() {

    private var _items: MutableLiveData<Resource<List<ListItem>>> = MutableLiveData()
    val items: LiveData<Resource<List<ListItem>>> get() = _items

    init {
        getItems()
    }

    private fun getItems() {
        viewModelScope.launch {
            repo.getItems().collect {
                _items.postValue(it)
            }
        }
    }
}

class ListItemViewModelFactory(private val listItemRepository: ListItemRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ListItemViewModel(listItemRepository) as T
    }
}