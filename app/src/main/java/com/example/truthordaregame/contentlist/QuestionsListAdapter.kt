package com.example.truthordaregame.contentlist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.truthordaregame.ContentType
import com.example.truthordaregame.R
import com.example.truthordaregame.database.Question

class QuestionsListAdapter(var viewModel: ContentListViewModel): RecyclerView.Adapter<ContentItemViewHolder>(){

    var data = listOf<Question>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ContentItemViewHolder, position: Int) {
        val item = data[position]
        holder.text.text = item.questionString

        holder.deleteButton.setOnClickListener {
            viewModel.onDeleteQuestion(item.questionID)
            Toast.makeText(viewModel.getApplication(), it.context.resources.getString(R.string.question_deleted_successfully), Toast.LENGTH_SHORT).show()
        }

        holder.card.setOnClickListener{
            it.findNavController().navigate(ContentListFragmentDirections.actionContentListFragmentToEditContentFragment(item.questionID, item.questionString, ContentType.QUESTION))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.text_item_view, parent, false) as CardView
        return ContentItemViewHolder(view)
    }
}
