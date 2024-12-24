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
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_item, parent, false)
        return GridViewHolder(view)
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        val item = items[position]
        holder.imageView.setImageResource(item.image)
        holder.titleView.text = item.title
        holder.priceView.text = buildString { //ეს თვითონ მირჩია, ვორნინგი რო გაექრო სეტ ტექსტზე
        append("$")
        append(item.price)
    }
    }

    override fun getItemCount(): Int = items.size
}
