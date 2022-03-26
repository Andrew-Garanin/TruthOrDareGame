package com.example.truthordaregame.contentlist

import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.truthordaregame.R

class ContentItemViewHolder(itemView: CardView): RecyclerView.ViewHolder(itemView) {
    val card: CardView = itemView.findViewById(R.id.cardItem)
    val text: TextView = itemView.findViewById(R.id.textItem)
    val deleteButton: Button = itemView.findViewById(R.id.deleteButton)
}