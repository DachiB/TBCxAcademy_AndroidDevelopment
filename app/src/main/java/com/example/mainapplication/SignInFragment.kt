package com.example.mainapplication

import android.os.Bundle
import android.text.InputType
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Toast
import androidx.fragment.app.Fragment

class SignInFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_in, container, false)

        val signIn = view.findViewById<Button>(R.id.button_sign_in)
        val backBtn = view.findViewById<Button>(R.id.backBtn)
        val emailInput = view.findViewById<EditText>(R.id.emailInput)
        val passwordInput = view.findViewById<EditText>(R.id.passwordInput)
        val passwordVisibility = view.findViewById<Button>(R.id.passwordVisibility)
        val emailContainer = view.findViewById<FrameLayout>(R.id.emailContainer)
        val passwordContainer = view.findViewById<FrameLayout>(R.id.passContainer)

        fun checkValues(): Boolean {
            return emailInput.text.toString().isNotEmpty() && passwordInput.text.toString()
                .isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailInput.text.toString())
                .matches()
        }

        fun passwordVisibilitySwitcher(){
            if (passwordInput.inputType and InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                passwordInput.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            } else {
                passwordInput.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            }
            passwordInput.setSelection(passwordInput.text?.length ?: 0)
        }

        fun checkSignIn(){
            if (checkValues()) {
                Toast.makeText(requireContext(), "Sign-in!", Toast.LENGTH_LONG).show()
                emailInput.text.clear()
                passwordInput.text.clear()
            } else {
                Toast.makeText(requireContext(), "Invalid Input!", Toast.LENGTH_LONG).show()
            }
        }


        emailInput.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                emailContainer.alpha = 1.0f
            } else {
                emailContainer.alpha = 0.5f
            }
        }

        passwordInput.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                passwordContainer.alpha = 1.0f
            } else {
                passwordContainer.alpha = 0.5f
            }
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

        return view
    }


}
