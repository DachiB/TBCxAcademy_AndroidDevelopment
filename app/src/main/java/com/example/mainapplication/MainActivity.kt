package com.example.mainapplication

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.mainapplication.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    private var users = mutableListOf(
        User(
            1,
            "გრიშა",
            "ონიანი",
            "1724647601641",
            "სტალინის სახლმუზეუმი",
            "grisha@mail.ru"
        ),
        User(
            id = 2,
            firstName = "Jemal",
            lastName = "Kakauridze",
            birthday = "1714647601641",
            address = "თბილისი, ლილოს მიტოვებული ქარხანა",
            email = "jemal@gmail.com"
        ),
        User(
            id = 2,
            firstName = "Omger",
            lastName = "Kakauridze",
            birthday = "1724647701641",
            address = "თბილისი, ასათიანი 18",
            email = "omger@gmail.com"
        ),
        User(
            id = 32,
            firstName = "ბორის",
            lastName = "გარუჩავა",
            birthday = "1714947701641",
            address = "თბილისი, იაშვილი 14",
            email = ""
        ),
        User(
            id = 34,
            firstName = "აბთო",
            lastName = "სიხარულიძე",
            birthday = "1711947701641",
            address = "ფოთი",
            email = "tebzi@gmail.com",
            desc = null
        )

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchInput.addTextChangedListener { text ->
            if (text.isNullOrEmpty()) {
                resetUI()
            } else {
                searchUser(text.toString())
            }
        }

        binding.addUser.setOnClickListener {
            binding.searchPage.visibility = View.GONE
            binding.addUserPage.visibility = View.VISIBLE
        }

        binding.saveButton.setOnClickListener {
            addUser()
        }

    }

    private fun addUser(){
        if (checkValues()) {
            binding.searchPage.visibility = View.VISIBLE
            binding.addUserPage.visibility = View.GONE
            val newUser = User(
                id = (1..1000).random(),
                firstName = binding.firstNameInput.text.toString(),
                lastName = binding.lastNameInput.text.toString(),
                desc = binding.descInput.text.toString(),
                email = binding.emailInput.text.toString(),
                address = binding.address.text.toString(),
                birthday = binding.birthday.text.toString()
            )

            users.add(newUser)
        } else {
            Toast.makeText(this, "InvalidInput", Toast.LENGTH_LONG).show()
        }
    }

    private fun resetUI() {
        binding.userInfo.visibility = View.GONE
        binding.userNotFound.visibility = View.GONE
        binding.addUser.visibility = View.GONE
    }

    private fun searchUser(user: String) {

        val userSearched = users.find {
            it.firstName.contains(user, ignoreCase = true) || it.lastName.contains(
                user,
                ignoreCase = true
            ) || it.birthday.contains(user, ignoreCase = true) || it.address.contains(
                user,
                ignoreCase = true
            ) || it.email.contains(user, ignoreCase = true) || it.desc?.contains(
                user,
                ignoreCase = true
            ) == true
        }

        if (userSearched != null) {
            displayUserInfo(userSearched)
        } else {
            showNoUserFound()
        }

    }

    private fun displayUserInfo(user: User) {
        binding.userInfo.text =
            "ID: ${user.id}\nName: ${user.firstName} ${user.lastName}\nEmail: ${user.email}"
        binding.userInfo.visibility = View.VISIBLE
        binding.userNotFound.visibility = View.GONE
        binding.addUser.visibility = View.GONE
    }

    private fun showNoUserFound() {
        binding.userInfo.visibility = View.GONE
        binding.userNotFound.visibility = View.VISIBLE
        binding.addUser.visibility = View.VISIBLE
    }

    private fun checkValues(): Boolean {
        return binding.emailInput.text.toString()
            .isNotEmpty() && binding.firstNameInput.text.toString().isNotEmpty() &&
                binding.lastNameInput.text.toString().isNotEmpty() &&
                binding.birthday.text.toString().isNotEmpty() &&
                binding.address.text.toString().isNotEmpty() &&
                binding.descInput.text.toString().isNotEmpty()
    }
}