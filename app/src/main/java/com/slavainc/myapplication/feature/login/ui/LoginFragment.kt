package com.slavainc.myapplication.feature.login.ui

import android.os.Bundle
import android.view.View
import com.slavainc.myapplication.R
import com.slavainc.myapplication.core.base.fragment.BaseBindingFragment
import com.slavainc.myapplication.databinding.LoginFragmentBinding

class LoginFragment : BaseBindingFragment<LoginFragmentBinding>() {

    override val assignLayoutId = R.layout.login_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//
//        binding.submitProgressBar.visibility = View.GONE
//        binding.submit.setOnClickListener {
//            val email = binding.email.text.toString()
//            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//                binding.emailLayout.error = getString(R.string.invalid_email)
//                return@setOnClickListener
//            }
//
//            binding.submitProgressBar.visibility = View.VISIBLE
//            binding.submit.visibility = View.GONE
//            lifecycleScope.launchWhenResumed {
//                val response = try {
//                    apolloClient(requireContext()).mutate(LoginMutation(email = Input.fromNullable(email))).toDeferred()
//                        .await()
//                } catch (e: Exception) {
//                    null
//                }
//
//                val login = response?.data?.login
//                if (login == null || response.hasErrors()) {
//                    binding.submitProgressBar.visibility = View.GONE
//                    binding.submit.visibility = View.VISIBLE
//                    return@launchWhenResumed
//                }
//
//                User.setToken(requireContext(), login)
//                findNavController().popBackStack()
//            }
//        }
    }
}