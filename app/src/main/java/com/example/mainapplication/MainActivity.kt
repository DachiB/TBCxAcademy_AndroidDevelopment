package com.example.mainapplication

import android.os.Bundle
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
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myArray = mutableListOf<String>()

        binding.save.setOnClickListener {
            val words = if (binding.words.text?.contains(",") == true) {
                binding.words.text!!.trim().split(",").toList()
                } else {
                listOf(binding.words.text.toString())
            }
            for (word in words){
                myArray.add(word.trim())
            }
            binding.words.text?.clear()
            binding.entry.text = myArray.toString()
        }

        binding.clear.setOnClickListener {
            myArray.clear()
            binding.entry.text = ""
            binding.result.removeAllViews()
        }

        binding.output.setOnClickListener {
            binding.result.removeAllViews()
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
                binding.result.addView(textView)
            }

            binding.count.text = "Group Count: ${groupedAnagrams.size}"
        }
    }
}


