package com.example.pairassginment.supervisor.recycleAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.pairassginment.R
import com.example.pairassginment.supervisor.StudentWork
import com.example.pairassginment.supervisor.`object`.StudWorkClass
import com.google.firebase.database.collection.LLRBNode.Color

class StudWorkAdapter(val items: ArrayList<StudWorkClass>, val listener: OnItemClickListener) :

    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.supervisor_single_student_row, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            when(items[position].itemImage){
                "Proposal_PPT"-> holder.image.setImageResource(R.drawable.ppt)
                "Proposal"-> holder.image.setImageResource(R.drawable.txt)
                "Final_PPT"-> holder.image.setImageResource(R.drawable.ppt)
                "Final_Draft"-> holder.image.setImageResource(R.drawable.txt)
                "Final_Thesis" -> holder.image.setImageResource(R.drawable.thesis)
                "Poster" -> holder.image.setImageResource(R.drawable.brochure)
                else -> holder.image.setImageResource(R.drawable.idea)

            }
            when(items[position].status) {
                "APPROVED" ->holder.card_view1.setCardBackgroundColor(android.graphics.Color.parseColor("#57e655"))
                "REJECTED" ->holder.card_view1.setCardBackgroundColor(android.graphics.Color.parseColor("#cc4343"))
                "PENDING" ->holder.card_view1.setCardBackgroundColor(android.graphics.Color.parseColor("#ede65c"))
                else -> holder.card_view1.setBackgroundColor(android.graphics.Color.parseColor("#adada5"))
            }

            holder.topic.text = items[position].itemTopic
            holder.mark.text = items[position].itemMark
            holder.subDate.text = items[position].itemSubDate
            holder.approveRejectDate.text = items[position].itemApproveRejectDate
        }
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var card_view1: CardView
        var image: ImageView
        var topic: TextView
        var mark: TextView
        var subDate: TextView
        var approveRejectDate: TextView

        init {
            card_view1 = itemView.findViewById(R.id.card_view)
            image = itemView.findViewById(R.id.item_image)
            topic = itemView.findViewById(R.id.itemTopicSubmitted)
            mark = itemView.findViewById(R.id.mark_tv)
            subDate = itemView.findViewById(R.id.item_submitted_date_tv)
            approveRejectDate = itemView.findViewById(R.id.item_approved_rejected_date_tv)
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION){
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}
