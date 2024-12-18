package com.example.mainapplication

import android.os.Bundle
import android.util.Patterns
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.mainapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        val valuesArr = listOf(
            binding.first.text, binding.last.text, binding.age.text, binding.email.text
        )

        val usersArr = mutableListOf<User>()

        fun checkValues(): Boolean {
            return valuesArr.all { it.toString().isNotEmpty() } && Patterns.EMAIL_ADDRESS.matcher(
                binding.email.text.toString()
            ).matches()
        }

        fun clearFields() {
            valuesArr.forEach { it?.clear() }
        }

        fun showResult(result: String, colorID: Int) {
            binding.result.text = result
            binding.result.setTextColor(getColor(colorID))
        }

        fun invalidFields() {
            showResult(getString(R.string.fill_all_fields), R.color.red)
        }

        fun addUser() {
            val user = User(
                binding.first.text.toString(),
                binding.last.text.toString(),
                binding.age.text.toString().toInt(),
                binding.email.text.toString()
            )
            // აქაც იუზერებს ვამოწმებ იმეილით რადგან ეგ იყოს უნიკალური, რადგან იუზერების ასაკი და სახელი შეიძლება დაემთხვეს
            val userExists = usersArr.any { it.email.lowercase() == user.email.lowercase() }
            if (!userExists) {
                usersArr.add(user)
                showResult(getString(R.string.user_added_succesfully), R.color.green)
                binding.activeUsers.text = "Active Users: ${usersArr.size}"
                clearFields()
            } else {
                showResult(getString(R.string.user_already_exists), R.color.red)
            }
        }

        fun removeUser() {
            var deletedUsers = 0
            val user = User(
                binding.first.text.toString(),
                binding.last.text.toString(),
                binding.age.text.toString().toInt(),
                binding.email.text.toString()
            )
            val userExists = usersArr.find { it == user }
            if (userExists != null) {
                deletedUsers++
                usersArr.remove(user)
                showResult(getString(R.string.user_deleted_succesfully), R.color.green)
                clearFields()
                binding.activeUsers.text = "Active Users: ${usersArr.size}"
                binding.deletedUsers.text = "Deleted Users: $deletedUsers"
            } else {
                showResult(getString(R.string.user_doesn_t_exist), R.color.red)
            }
        }

        //შემოწმება გავაკეთე იმეილით, და ინფუთებში მითთებული ახალი პარამეტრები ავტომატურად ენიჭება კონკრეტულ ობიექტს
        fun updateUser() {
            val user =
                usersArr.find { it.email.lowercase() == binding.email.text.toString().lowercase() }
            if (user != null) {
                user.firstName = binding.first.text.toString()
                user.lastName = binding.last.text.toString()
                user.age = binding.age.text.toString().toInt()
                showResult(getString(R.string.user_updated_succesfully), R.color.green)
                clearFields()
            } else {
                showResult(getString(R.string.can_t_update_non_existing_user), R.color.red)
            }
        }

        //ფუნქციონალის შესამოწმებლად დავამატე მოცემული იუზერების გამოტანა კლიკზე
        fun showUsers() {
            binding.linear.removeAllViews()
            for (user in usersArr) {
                val text = TextView(this)
                text.text = user.toString()
                text.setTextColor(getColor(R.color.white))
                binding.linear.addView(text)
            }
        }

        binding.add.setOnClickListener {
            if (checkValues()) {
                addUser()
            } else {
                invalidFields()
            }
        }

        binding.remove.setOnClickListener {
            if (checkValues()) {
                removeUser()
            } else {
                invalidFields()
            }
        }

        binding.update.setOnClickListener {
            if (checkValues()) {
                updateUser()
            } else {
                invalidFields()
            }
        }

        binding.show.setOnClickListener {
            showUsers()
        }


    }
}
