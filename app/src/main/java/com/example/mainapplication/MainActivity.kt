package com.example.mainapplication

import android.os.Bundle
import android.util.Patterns
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.mainapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        data class Person(val name: String, val email: String)

        val usersArr = mutableListOf<Person>()

        fun checkUser(name: String, email: String): Boolean {
            var userExists = false
            for (item in usersArr) {
                if (item.name == name || item.email == email) {
                    userExists = true
                }
            }
            return userExists
        }

        fun checkValues() {
            if (binding.name.text?.isEmpty() == true || binding.email.text?.isEmpty() == true || !Patterns.EMAIL_ADDRESS.matcher(binding.email.text.toString()).matches()) {
                binding.result.text = "Please enter valid values"
            } else if (checkUser(binding.name.text.toString(), binding.email.text.toString())) {
                    binding.result.text = "User already exists"
                } else {
                    usersArr.add(Person(binding.name.text.toString(), binding.email.text.toString()))
                    binding.userCount.text = "User Count: ${usersArr.size}"
                    binding.result.text = "User Added"
                binding.name.text?.clear()
                binding.email.text?.clear()
                }
            }



        binding.addUser.setOnClickListener {
            checkValues()
        }

        binding.getUser.setOnClickListener {
            var found = false
            for (item in usersArr) {
                if (item.email == binding.findEmail.text.toString()) {
                    binding.userInfo.text = "Name: ${item.name} \nEmail: ${item.email}"
                    binding.findEmail.text?.clear()
                    found = true
                    break
                }
                }
            if (!found) {
                binding.userInfo.text = "User not found"
            }
        }
    }
}

