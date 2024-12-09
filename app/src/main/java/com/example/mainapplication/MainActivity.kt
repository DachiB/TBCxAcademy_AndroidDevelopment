package com.example.mainapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.mainapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val words = findViewById<EditText>(R.id.words)
        val save = findViewById<Button>(R.id.save)
        val output = findViewById<Button>(R.id.output)
        val result = findViewById<LinearLayout>(R.id.result)
        val entry = findViewById<TextView>(R.id.entry)
        val clear = findViewById<Button>(R.id.clear)

        val myArray = mutableListOf<String>()

        save.setOnClickListener {
            val word = words.text.toString()
            if (word.isNotEmpty()) {
                myArray.add(word)
                words.text.clear()
            }
            entry.text = myArray.toString()
        }

        clear.setOnClickListener {
            myArray.clear()
            entry.text = ""
            result.removeAllViews()
        }

        output.setOnClickListener {
            result.removeAllViews()
            val groupedAnagrams = mutableListOf<MutableList<String>>()

            for (i in myArray.indices) {
                var isGrouped = false

                for (group in groupedAnagrams) {
                    if (group[0].toCharArray().sorted() == myArray[i].toCharArray().sorted()) {
                        group.add(myArray[i])
                        isGrouped = true
                        break
                    }
                }
                if (!isGrouped) {
                    groupedAnagrams.add(mutableListOf(myArray[i]))
                }
            }

            for (group in groupedAnagrams) {
                val textView = TextView(this)
                textView.text = group.toString()
                result.addView(textView)
            }
        }
    }
}


