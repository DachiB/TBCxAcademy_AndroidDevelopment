package com.example.mainapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mainapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            val fragment = LogInFragment()
            val bundle = Bundle()
            bundle.putString("log_key", "Log In")
            bundle.putString("name_key", "Log In")
            bundle.putBoolean("hasNext", false)
            fragment.arguments = bundle
            supportFragmentManager.beginTransaction()
                .replace(R.id.main, fragment)
                .addToBackStack("MainActivity")
                .commit()
        }

        binding.registerButton.setOnClickListener {
            val fragment = LogInFragment()
            val bundle = Bundle()
            bundle.putString("log_key", "Register")
            bundle.putString("name_key", "Next")
            bundle.putBoolean("hasNext", true)
            fragment.arguments = bundle
            supportFragmentManager.beginTransaction()
                .replace(R.id.main, fragment)
                .addToBackStack("MainActivity")
                .commit()
        }
    }
}