package com.slavainc.myapplication.feature.login.ui

import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.navigation.fragment.findNavController
import com.slavainc.myapplication.R
import com.slavainc.myapplication.core.base.fragment.BaseBindingFragment
import com.slavainc.myapplication.databinding.LoginFragmentBinding
import com.slavainc.myapplication.feature.login.vm.LoginViewModel
import com.slavainc.myapplication.utils.Resource.Status.*
import com.slavainc.myapplication.utils.observe
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment : BaseBindingFragment<LoginFragmentBinding>() {

    override val assignLayoutId = R.layout.login_fragment

    private val loginViewModel: LoginViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.submitProgressBar.visibility = View.GONE

        binding.submit.setOnClickListener {
            val email = binding.email.text.toString()
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.emailLayout.error = getString(R.string.invalid_email)
                return@setOnClickListener
            }
            binding.submitProgressBar.visibility = View.VISIBLE
            binding.submit.visibility = View.GONE
            loginViewModel.login(email)
        }
    }

    override fun observeViewModel() {
        observe(loginViewModel.loginLD) { resource ->
            when (resource.status) {
                SUCCESS -> findNavController().popBackStack()

                ERROR -> {
                    binding.submitProgressBar.visibility = View.GONE
                    binding.submit.visibility = View.VISIBLE
                }
            }
        }
    }
}