package com.example.mainapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GridAdapter(private val items: List<Item>) :
    RecyclerView.Adapter<GridAdapter.GridViewHolder>() {

    class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleView: TextView = itemView.findViewById(R.id.itemTitle)
        val imageView: ImageView = itemView.findViewById(R.id.itemImage)
        val priceView: TextView = itemView.findViewById(R.id.itemPrice)
        val favoriteButton: ImageView = itemView.findViewById(R.id.favoriteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_item, parent, false)
        return GridViewHolder(view)
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        val item = items[position]
        holder.apply {
            imageView.setImageResource(item.image)
            favoriteButton.setImageResource(if (item.favorite) R.drawable.icon_heart_fill else R.drawable.icon_heart)
            titleView.text = item.title
            priceView.text = "$${item.price}"
            favoriteButton.setOnClickListener {
                if (!item.favorite) {
                    holder.favoriteButton.setImageResource(R.drawable.icon_heart_fill)
                    item.favorite = true
                } else {
                    holder.favoriteButton.setImageResource(R.drawable.icon_heart)
                    item.favorite = false
                }
            }
        }

    }

    override fun getItemCount(): Int = items.size
}
