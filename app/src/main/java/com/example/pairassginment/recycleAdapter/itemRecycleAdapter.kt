package com.example.pairassginment.recycleAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pairassginment.R
import com.example.pairassginment.databinding.CardLayoutBinding
import android.content.Context
import com.example.pairassginment.student.three_topic_item

class itemRecycleAdapter (val context: Context, val items: ArrayList<three_topic_item>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = CardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(LayoutInflater.from(context).inflate(
            R.layout.card_layout,
            parent,
            false
        ), binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if(holder is ViewHolder){
            holder.itemImage.setImageResource(R.drawable.idea)
            holder.itemTopic.text = (items[position].topicSubmitted)
            holder.itemSubmittedDate.text = (items[position].dateSubmitted)
            holder.itemApprovedDate.text = (items[position].dateApproved)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

     class ViewHolder(itemView: View, binding: CardLayoutBinding): RecyclerView.ViewHolder(binding.root){
        val itemImage = binding.itemImage
        val itemTopic = binding.itemTopicSubmitted
        val itemSubmittedDate = binding.itemSubmittedDate
        val itemApprovedDate = binding.itemApprovedDate
    }
}