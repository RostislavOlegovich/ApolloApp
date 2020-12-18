package com.slavainc.myapplication.feature.main.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.slavainc.myapplication.R
import com.slavainc.myapplication.core.base.fragment.BaseBindingFragment
import com.slavainc.myapplication.databinding.LaunchListFragmentBinding
import com.slavainc.myapplication.feature.main.vm.MainViewModel
import com.slavainc.myapplication.utils.Resource.Status.*
import com.slavainc.myapplication.utils.observe
import kotlinx.coroutines.channels.Channel
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class LaunchListFragment : BaseBindingFragment<LaunchListFragmentBinding>() {

    override val assignLayoutId = R.layout.launch_list_fragment

    private lateinit var adapter: LaunchListAdapter
    private val mainViewModel: MainViewModel by sharedViewModel()

    private var cursor: String? = null

    private val channel = Channel<Unit>(Channel.CONFLATED)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()

        channel.offer(Unit)
        adapter.onEndOfListReached = {
            channel.offer(Unit)
        }

        lifecycleScope.launchWhenResumed {
            for (item in channel) {
                mainViewModel.launchList(cursor)
            }
        }

    }

    override fun observeViewModel() {
        observe(mainViewModel.launchesLD) { resource ->
            when (resource.status) {
                SUCCESS -> {
                    val newLaunches = resource.data?.launches?.filterNotNull()

                    if (newLaunches != null) adapter.addItems(newLaunches)

                    cursor = resource.data?.cursor

                    if (resource.data?.hasMore != true) {
                        adapter.onEndOfListReached = null
                        channel.close()
                    }
                }
                ERROR -> {
                    Toast.makeText(requireContext(), resource.exception, Toast.LENGTH_SHORT).show()
                }
                LOADING -> { }
            }
        }
    }

    private fun setupAdapter() {
        adapter = LaunchListAdapter()
        binding.launches.layoutManager = LinearLayoutManager(requireContext())
        binding.launches.adapter = adapter
        adapter.onItemClicked = { launch ->
            findNavController().navigate(
                LaunchListFragmentDirections.openLaunchDetails(launchId = launch.id)
            )
        }
    }
}