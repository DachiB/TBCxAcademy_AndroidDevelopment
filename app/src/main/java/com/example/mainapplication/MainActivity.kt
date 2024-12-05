package com.example.mainapplication

import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

//მოვიძიო ინფო: Patterns.EMAIL_ADDRESS.matcher, editTextList.any და setOnLongClickListenerზე
//StackOverflow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val email: EditText = findViewById(R.id.email)
        val username: EditText = findViewById(R.id.userName)
        val firstName: EditText = findViewById(R.id.firstName)
        val lastName: EditText = findViewById(R.id.lastName)
        val age: EditText = findViewById(R.id.age)
        val save: Button = findViewById(R.id.save)
        val clear: Button = findViewById(R.id.clear)
        val result: TextView = findViewById(R.id.result)
        val texts: LinearLayout = findViewById(R.id.texts)
        val edits: LinearLayout = findViewById(R.id.edits)

        val editTextList = listOf(email, username, firstName, lastName, age)

        fun checkValues(): Boolean {
            val isNotEmpty = editTextList.any { it.text.isNotEmpty()}
            val checkNum = age.text.toString().isNotEmpty() //Null? ჩეკიც შეიძლეობდა?
            val userNameLength = username.text.length > 10
            val emailValid = Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()

            return userNameLength && emailValid && isNotEmpty && checkNum
        }

        fun clearValues() {
            editTextList.forEach { it.text.clear() }
        }

        fun generateResult() {
            texts.removeAllViews()
            texts.visibility = LinearLayout.VISIBLE
            edits.visibility = LinearLayout.GONE
            result.text = ""

            val header = TextView(this)
            header.text = "Profile Info"
            header.textSize = 20f
            header.setPadding(0, 16, 0, 16)
            texts.addView(header)

            var i = 0
            while (i < editTextList.size) {
                val textView = TextView(this)
                if (i == 2 && i + 1 < editTextList.size) {
                    textView.text = "Full Name : ${editTextList[i].text} ${editTextList[i + 1].text}"
                    i += 2
                } else {
                    textView.text = "${editTextList[i].hint} : ${editTextList[i].text}"
                    i++
                }
                texts.addView(textView)
            }

            val button = Button(this)
            button.text = "Again"
            texts.addView(button)

            button.setOnClickListener {
                texts.visibility = LinearLayout.GONE
                edits.visibility = LinearLayout.VISIBLE
                clearValues()
            }
        }

        save.setOnClickListener {
           if (checkValues()) {
               generateResult()
           } else {
              result.text = "მონაცემები არასწორია"
           }
        }

        clear.setOnLongClickListener {
            clearValues()
            true
        }
    }
}
