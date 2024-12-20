package com.example.mainapplication

import android.os.Bundle
import android.text.InputType
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mainapplication.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    //მოვიძიე ინფორმაცია რომ ყოველ ჯერზე არმომიწიოს ნულზე შემოწმება
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)

        val signIn = binding.buttonSignIn
        val backBtn = binding.backBtn
        val emailInput = binding.emailInput
        val passwordInput = binding.passwordInput
        val passwordVisibility = binding.passwordVisibility
        val emailContainer = binding.emailContainer
        val passwordContainer = binding.passContainer

        fun checkValues(): Boolean {
            return emailInput.text.toString().isNotEmpty() && passwordInput.text.toString()
                .isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailInput.text.toString())
                .matches()
        }

        fun passwordVisibilitySwitcher() {
            if (passwordInput.inputType and InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                passwordInput.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            } else {
                passwordInput.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            }
            passwordInput.setSelection(passwordInput.text?.length ?: 0)
        }

        fun checkSignIn() {
            if (checkValues()) {
                Toast.makeText(requireContext(), "Sign-in!", Toast.LENGTH_LONG).show()
                emailInput.text?.clear()
                passwordInput.text?.clear()
            } else {
                Toast.makeText(requireContext(), "Invalid Input!", Toast.LENGTH_LONG).show()
            }
        }

        emailInput.setOnFocusChangeListener { _, hasFocus ->
            emailContainer.alpha = if (hasFocus) 1.0f else 0.5f
        }

        passwordInput.setOnFocusChangeListener { _, hasFocus ->
            passwordContainer.alpha = if (hasFocus) 1.0f else 0.5f
        }

        backBtn.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        passwordVisibility.setOnClickListener {
            passwordVisibilitySwitcher()
        }

        signIn.setOnClickListener {
            checkSignIn()
        }

        return binding.root
    }

}

