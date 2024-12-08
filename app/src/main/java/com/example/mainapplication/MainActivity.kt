package com.example.mainapplication

import android.app.Person
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val name = findViewById<EditText>(R.id.name)
        val email = findViewById<EditText>(R.id.email)
        val userCount = findViewById<TextView>(R.id.userCount)
        val addUser = findViewById<Button>(R.id.addUser)
        val result = findViewById<TextView>(R.id.result)
        val findEmail = findViewById<EditText>(R.id.findEmail)
        val getUser = findViewById<Button>(R.id.getUser)
        val userInfo = findViewById<TextView>(R.id.userInfo)

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
            if (name.text.isEmpty() || email.text.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {
                result.text = "Please enter valid values"
            } else if (checkUser(name.text.toString(), email.text.toString())) {
                    result.text = "User already exists"
                } else {
                    usersArr.add(Person(name.text.toString(), email.text.toString()))
                    userCount.text = "User Count: ${usersArr.size}"
                    result.text = "User Added"
                    name.text.clear()
                    email.text.clear()
                }
            }



        addUser.setOnClickListener {
            checkValues()
        }

        getUser.setOnClickListener {
            var found = false
            for (item in usersArr) {
                if (item.email == findEmail.text.toString()) {
                    userInfo.text = "Name: ${item.name} \nEmail: ${item.email}"
                    findEmail.text.clear()
                    found = true
                    break
                }
                }
            if (!found) {
                userInfo.text = "User not found"
            }
        }
    }
}

