package com.example.pairassginment.recycleAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pairassginment.R
import com.example.pairassginment.databinding.CardLayoutBinding
import android.content.Context
import android.graphics.Color
import android.view.View.OnClickListener
import androidx.core.content.ContextCompat
import com.example.pairassginment.student.ThreeTopicsItem

class itemRecycleAdapter (val context: Context, val items: ArrayList<ThreeTopicsItem>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var mListener : onItemClickListner

    interface onItemClickListner{
        fun onItemClick(position: Int)
    }

    fun setOnClickListener(listener: onItemClickListner){
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = CardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(LayoutInflater.from(context).inflate(
            R.layout.card_layout,
            parent,
            false
        ), mListener, binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if(holder is ViewHolder){
            holder.itemImage.setImageResource(R.drawable.idea)
            holder.itemTopic.text = (items[position].topicSubmitted)
            holder.itemSubmittedDate.text = context.getString(R.string.submitted_date, items[position].dateSubmitted)

            when (items[position].submittedStatus){
                "Approved" ->
                    {holder.itemApprovedRejectedDate.text = context.getString(R.string.approved_date, items[position].dateApproved)
                        holder.itemsBngColor.setCardBackgroundColor(context.getColor(R.color.approved_green))}

                "Pending" ->
                holder.itemsBngColor.setCardBackgroundColor(context.getColor(R.color.pending_yellow))

                else ->
                {holder.itemApprovedRejectedDate.text = context.getString(R.string.rejected_date, items[position].dateReject)
                    holder.itemsBngColor.setCardBackgroundColor(context.getColor(R.color.rejected_red))}

            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

     class ViewHolder(itemView: View, listener: onItemClickListner ,binding: CardLayoutBinding): RecyclerView.ViewHolder(binding.root){
         val itemImage = binding.itemImage
         val itemTopic = binding.itemTopicSubmitted
         val itemSubmittedDate = binding.itemSubmittedDate
         val itemApprovedRejectedDate = binding.itemApprovedRejectedDate
         val itemsBngColor = binding.cardView

         init {
             binding.cardView.setOnClickListener{
                 listener.onItemClick(adapterPosition)
             }
         }
    }
}