package com.example.mainapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mainapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemList = listOf(
            Item("Belt suit blazer", "Category 1", R.drawable.icon_image),
            Item("Tailored Blazer", "Category 1", R.drawable.icon_image2),
            Item("Button-up Shirt", "Party", R.drawable.icon_image3),
            Item("A-Line Dress", "Party", R.drawable.icon_image7),
            Item("Wool Sweater", "Party", R.drawable.icon_image6),
            Item("Bomber Jacket", "Camping", R.drawable.icon_image4),
            Item("Pleated Skirt", "Camping", R.drawable.icon_image7),
            Item("Cargo Pants", "Camping", R.drawable.icon_image),
            Item("V-neck Sweater", "Category 2", R.drawable.icon_image5),
            Item("Graphic T-shirt", "Category 1", R.drawable.icon_image7),
            Item("Ch Coat", "Category 1", R.drawable.icon_image2),
            Item("Luigi Coat", "Category 3", R.drawable.icon_image4),
            Item("Shelf Coat", "Category 3", R.drawable.icon_image5),
            Item("Welch Coat", "Category 2", R.drawable.icon_image6),
            Item("ITrench Coat", "Category 2", R.drawable.icon_image4),
            Item("Just-A-Coat", "Category 3", R.drawable.icon_image6),
        )

        val categoryList =
            listOf(
                "All",
                "Party",
                "Camping",
                "Category 1",
                "Category 2",
                "Category 3"
            )

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        setUpItems(recyclerView, itemList)

        categoryList.forEach {
            setUpCategories(recyclerView, it, itemList)
        }
    }

    private fun setUpItems(view: RecyclerView, itemList: List<Item>) {
        view.adapter = GridAdapter(itemList)
    }

    private fun filterItems(view: RecyclerView, itemDescription: String, itemList: List<Item>) {
        val filteredList =
            itemList.filter { it.category.contains(itemDescription, ignoreCase = true) }
        view.adapter = GridAdapter(filteredList)
    }

    private fun setUpCategories(view: RecyclerView, category: String, itemList: List<Item>) {
        val button = Button(this)
//        button.id = View.generateViewId()
        button.text = category
        button.background = AppCompatResources.getDrawable(this, R.drawable.bordered_blue)
        button.setTextColor(getColor(R.color.buttonColor))
        button.setPadding(24, 8, 24, 8)

        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(16, 8, 16, 8)  // Set margins (left, top, right, bottom)
        button.layoutParams = layoutParams

        button.setOnClickListener {
            if (button.text == "All") {
                setUpItems(view, itemList)
            } else {
                filterItems(view, button.text.toString(), itemList)
            }
        }

        binding.scroll.addView(button)
    }
}

