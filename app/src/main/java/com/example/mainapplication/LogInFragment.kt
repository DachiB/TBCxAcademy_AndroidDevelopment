package com.example.mainapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.mainapplication.databinding.FragmentLogInBinding


class LogInFragment : Fragment() {

    private val users = mutableListOf<User>()
    private var binding: FragmentLogInBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLogInBinding.inflate(inflater, container, false)
        var switch = false
        val hasNext = arguments?.getBoolean("hasNext")
        val header = arguments?.getString("log_key")
        val name = arguments?.getString("name_key")
        Toast.makeText(requireContext(), users.toString(), Toast.LENGTH_LONG).show()

        binding?.headerText?.text = header
        binding?.submitButton?.text = name


        fun nextPage() {
            if (hasNext == true) {
                switch = true
                binding?.emailInput?.visibility = View.GONE
                binding?.passwordInput?.visibility = View.GONE
                binding?.nameInput?.visibility = View.VISIBLE
                binding?.agreementText?.visibility = View.VISIBLE
                binding?.submitButton?.text = getString(R.string.sign_up)
            }
        }

        fun previousPage() {
            switch = false
            binding?.emailInput?.visibility = View.VISIBLE
            binding?.passwordInput?.visibility = View.VISIBLE
            binding?.nameInput?.visibility = View.GONE
            binding?.agreementText?.visibility = View.GONE
            binding?.submitButton?.text = getString(R.string.next)
        }

        fun checkEmail(): Boolean {
            val emailRegex = Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
            return emailRegex.matches(binding?.emailInput?.text.toString()) && binding?.emailInput?.text.toString()
                .isNotEmpty()
        }

        fun checkPassword(): Boolean {
            return binding?.passwordInput?.text.toString().length > 5
        }

        fun checkUsername(): Boolean {
            return binding?.nameInput?.text.toString().isNotEmpty()
        }

        binding?.submitButton?.setOnClickListener {
            if (hasNext == true) {
                if (!switch) {
                    if (checkEmail() && checkPassword()) {
                        nextPage()
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Invalid Input",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } else {
                    if (checkUsername()) {
                        val userName = binding?.nameInput?.text.toString()
                        val email = binding?.emailInput?.text.toString()
                        val password = binding?.passwordInput?.text.toString()
                        val user = User(userName, email, password)
                        users.add(user)
                        Toast.makeText(
                            requireContext(),
                            "Registration Successful",
                            Toast.LENGTH_LONG
                        ).show()
                        parentFragmentManager.popBackStack()
                        Toast.makeText(requireContext(), users.toString(), Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(requireContext(), "Invalid Input", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            } else {
                if (checkEmail() && checkPassword()) {
                    val inputEmail = binding?.emailInput?.text.toString().trim().lowercase()
                    val user = users.find { it.email.trim().lowercase() == inputEmail }
                    if (user != null) {
                        Toast.makeText(requireContext(), "Login Successful", Toast.LENGTH_LONG)
                            .show()
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Invalid Email or Password",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } else {
                    Toast.makeText(requireContext(), "Invalid Input", Toast.LENGTH_LONG).show()
                }

            }
        }

        binding?.backButton?.setOnClickListener {
            if (switch) {
                previousPage()
            } else {
                parentFragmentManager.popBackStack()
            }
        }

        return binding?.root
    }
}