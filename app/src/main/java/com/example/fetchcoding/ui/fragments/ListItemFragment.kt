package com.example.fetchcoding.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fetchcoding.adapter.ListItemAdapter
import com.example.fetchcoding.databinding.FragmentListItemsBinding
import com.example.fetchcoding.databinding.ListItemBinding
import com.example.fetchcoding.remote.ListItemManager
import com.example.fetchcoding.repository.ListItemRepository
import com.example.fetchcoding.viewmodel.ListItemViewModel
import com.example.fetchcoding.viewmodel.ListItemViewModelFactory

class ListItemFragment : Fragment() {

    private var _binding: FragmentListItemsBinding? = null
    private val binding get() = _binding!!

    private lateinit var listItemViewModel: ListItemViewModel
    private lateinit var listItemAdapter: ListItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListItemsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listItemViewModel = ViewModelProvider(
            this,
            ListItemViewModelFactory(ListItemRepository(ListItemManager()))
        ).get(ListItemViewModel::class.java)

        setupRecyclerView()

        listItemViewModel.items.observe(viewLifecycleOwner, Observer {
            listItemAdapter.differ.submitList(it.data)
        })
    }

    private fun setupRecyclerView() = binding.rvListItems.apply {
        listItemAdapter = ListItemAdapter()
        adapter = listItemAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}