package com.example.mainapplication

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.SwitchCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputEditText: EditText = findViewById(R.id.inputEditText)
        val outputTextView: TextView = findViewById(R.id.outputTextView)
        val toggleSwitch: SwitchCompat = findViewById(R.id.languageToggle)
        val convertButton: AppCompatButton = findViewById(R.id.convertButton)

        convertButton.setOnClickListener {
            val inputText = inputEditText.text.toString()
            val number = inputText.toIntOrNull()

            if (number != null && number in 1..1000) {
                outputTextView.text = if (toggleSwitch.isChecked) {
                    numberToEng(number)
                } else {
                    numberToGeo(number)
                }
            } else {
                outputTextView.text = if (toggleSwitch.isChecked) {
                    "Please enter a number between 1 and 1000"
                } else {
                    "შეიყვანეთ რიცხვი 1-დან 1000-მდე"
                }
            }
        }
    }

    private fun numberToGeo(number: Int): String {
        val units = arrayOf(
            "", "ერთი", "ორი", "სამი", "ოთხი", "ხუთი", "ექვსი", "შვიდი", "რვა", "ცხრა"
        )
        val teens = arrayOf(
            "ათი", "თერთმეტი", "თორმეტი", "ცამეტი", "თოთხმეტი", "თხუთმეტი",
            "თექვსმეტი", "ჩვიდმეტი", "თვრამეტი", "ცხრამეტი"
        )
        val tens = arrayOf(
            "", "", "ოცი", "ოცდაათი", "ორმოცი", "ორმოცდაათი", "სამოცი",
            "სამოცდაათი", "ოთხმოცი", "ოთხმოცდაათი"
        )

        return when {
            number == 1000 -> "ათასი"
            number >= 100 -> {
                val hundredCheck = if (number < 200) {
                    "ასი"
                } else if (number >= 800) {
                    units[number / 100] + "ასი"
                } else {
                    units[number / 100].dropLast(1) + "ასი"
                }

                val indexCheck = number % 100
                val indexCheck2 = number % 10
                if (indexCheck == 0)
                {
                    return hundredCheck
                } else if (indexCheck2 == 0) {
                    return "${hundredCheck.dropLast(1)} ${numberToGeo(indexCheck)}"
                }
                else {
                    return "${hundredCheck.dropLast(1)} ${numberToGeo(indexCheck)}"
                }
            }
            number >= 20 -> {
                val tenPart = tens[number / 10]
                val unitPart = if(number % 20 < 10) units[number % 10] else teens[number % 10]
                return if(number % 20 < 10) {
                    "${tenPart.dropLast(1)}და $unitPart"
                } else {
                    "${tenPart.dropLast(3)} $unitPart"
                }
            }
            number >= 10 -> teens[number - 10]
            else -> units[number]
        }
    }

    private fun numberToEng(number: Int): String {
        val units = arrayOf(
            "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"
        )
        val teens = arrayOf(
            "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen",
            "sixteen", "seventeen", "eighteen", "nineteen"
        )
        val tens = arrayOf(
            "", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy",
            "eighty", "ninety"
        )

        return when {
            number == 1000 -> "one thousand"
            number >= 100 -> {
                val hundredCheck = units[number / 100] + " hundred"
                val indexCheck = number % 100
                if (indexCheck == 0) hundredCheck else "$hundredCheck and ${numberToEng(indexCheck)}"
            }
            number >= 20 -> {
                val tenPart = tens[number / 10]
                val unitPart = units[number % 10]
                "$tenPart $unitPart".trim()
            }
            number >= 10 -> teens[number - 10]
            else -> units[number]
        }
    }
}