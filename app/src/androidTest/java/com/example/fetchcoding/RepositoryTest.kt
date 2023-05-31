package com.example.fetchcoding

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.fetchcoding.remote.ListItemManager
import com.example.fetchcoding.repository.ListItemRepository
import com.example.fetchcoding.utils.Resource
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.unmockkAll
import kotlinx.coroutines.flow.flow
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class RepositoryTest {

    @RelaxedMockK
    lateinit var listItemManager: ListItemManager

    @RelaxedMockK
    lateinit var listItemRepository: ListItemRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun check(){
        every {
            listItemRepository.getItems()
        } returns flow {
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

    @After
    fun tearDown() {
        unmockkAll()
    }
}