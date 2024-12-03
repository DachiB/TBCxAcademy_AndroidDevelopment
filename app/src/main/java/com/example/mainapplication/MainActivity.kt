package com.example.mainapplication

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setup()
    }

    private fun setup() {
        val input: EditText = findViewById(R.id.editText)
        val button: AppCompatButton = findViewById(R.id.Submit)
        val textView: TextView = findViewById(R.id.textView)

        button.setOnClickListener {
            textView.text = convertNumber(input.text.toString().toInt())
        }
    }

    private fun convertNumber(number: Int): String {
        var result = ""
        val ciprebi = listOf(
            "ერთი", "ორი", "სამი" ,
            "ოთხი" , "ხუთი", "ექვსი",
            "შვიდი", "რვა", "ცხრა")
        val ricxvebi = listOf(
            "თერთმეტი", "თორმეტი", "ცამეტი",
            "თოთხმეტი", "თხუთმეტი", "თექვმეტი",
            "ჩვიდმეტი", "თვრამეტი", "ცხრამეი")
        val ricxvebi2 = listOf(
            "ათი", "ოცი", "ოცდაათი",
            "ორმოცი", "ორმოცდაათი","სამოცი",
            "სამოცდაათი","ოთხმოცი","ოთხომცდაათი")
        val ricxvebi3 = listOf(
            "ასი", "ორასი", "სამასი",
            "ოთხასი", "ხუთასი","ექვსასი",
            "შვიდასი","რვაასი","ცხრაასი")

        if (number in 1..9) {
            result = ciprebi[number] + 1
        }

        return result
    }

}
