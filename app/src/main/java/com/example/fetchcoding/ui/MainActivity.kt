package com.example.fetchcoding.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fetchcoding.adapter.ListItemAdapter
import com.example.fetchcoding.databinding.ActivityMainBinding
import com.example.fetchcoding.remote.ListItemManager
import com.example.fetchcoding.repository.ListItemRepository
import androidx.lifecycle.Observer
import com.example.fetchcoding.viewmodel.ListItemViewModel
import com.example.fetchcoding.viewmodel.ListItemViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}