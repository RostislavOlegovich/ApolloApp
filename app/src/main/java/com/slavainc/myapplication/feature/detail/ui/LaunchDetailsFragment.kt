package com.slavainc.myapplication.feature.detail.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.api.load
import com.slavainc.myapplication.R
import com.slavainc.myapplication.core.base.fragment.BaseBindingFragment
import com.slavainc.myapplication.databinding.LaunchDetailsFragmentBinding
import com.slavainc.myapplication.feature.detail.vm.DetailViewModel
import com.slavainc.myapplication.utils.Resource.Status.*
import com.slavainc.myapplication.utils.observe
import com.slavainc.utils.User
import org.koin.android.viewmodel.ext.android.viewModel

class LaunchDetailsFragment : BaseBindingFragment<LaunchDetailsFragmentBinding>() {

    override val assignLayoutId = R.layout.launch_details_fragment

    private val detailViewModel: DetailViewModel by viewModel()
    private val args: LaunchDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailViewModel.launchDetail(args.launchId)
    }

    override fun observeViewModel() {
        observe(detailViewModel.launchDetailLD) { resource ->
            when (resource.status) {
                SUCCESS -> {
                    binding.progressBar.visibility = View.GONE

                    binding.missionPatch.load(resource.data?.mission?.missionPatch) {
                        placeholder(R.drawable.ic_placeholder)
                    }

                    binding.site.text = resource.data?.site
                    binding.missionName.text = resource.data?.mission?.name
                    val rocket = resource.data?.rocket
                    binding.rocketName.text = "🚀 ${rocket?.name} ${rocket?.type}"

                    resource.data?.isBooked?.also {
                        configureButton(it)
                    }
                }
                ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    binding.error.text = resource.exception
                    binding.error.visibility = View.VISIBLE
                }
                LOADING -> {
                    binding.bookButton.visibility = View.GONE
                    binding.bookProgressBar.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                    binding.error.visibility = View.GONE
                }
            }
        }
    }

    private fun configureButton(isBooked: Boolean) {
        binding.bookButton.visibility = View.VISIBLE
        binding.bookProgressBar.visibility = View.GONE

        binding.bookButton.text = if (isBooked) getString(R.string.cancel) else getString(R.string.book_now)

        binding.bookButton.setOnClickListener {
            if (User.getToken(requireContext()) == null) {
                findNavController().navigate(R.id.open_login)
                return@setOnClickListener
            }

            binding.bookButton.visibility = View.INVISIBLE
            binding.bookProgressBar.visibility = View.VISIBLE

            detailViewModel.checkTrip(args.launchId, isBooked)

            observeTrip(isBooked)
        }
    }

    private fun observeTrip(isBooked: Boolean) {
        observe(detailViewModel.tripLD) { resource ->
            when (resource.status) {
                SUCCESS -> configureButton(!isBooked)
                ERROR -> configureButton(isBooked)
            }
        }
    }
}