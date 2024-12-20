package com.example.mainapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val signIn = view.findViewById<Button>(R.id.button_sign_in)
        val singUp = view.findViewById<Button>(R.id.singUp)

        signIn.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SignInFragment())
                .addToBackStack("HomeFragment")
                .commit()
        }
        singUp.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SignInFragment())
                .addToBackStack("HomeFragment")
                .commit()
        }

        return view
    }
}

