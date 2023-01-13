package com.example.pairassginment.coordinator.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.pairassginment.R
import com.example.pairassginment.coordinator.objectClass.StudentData
import com.example.pairassginment.student.itemRecycleAdapter

class CoordinatorAdapter(val items: ArrayList<StudentData>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var mListener : onItemClickListner

    interface onItemClickListner{
        fun onItemClick(position: Int)
    }

    fun setOnClickListener(listener: onItemClickListner){
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.coordinator_dashboard_recycle_view_row, parent, false)
        return ViewHolder(itemView, mListener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            holder.itemTitle.setText(items[position].name)
            holder.itemMark.setText(items[position].mark.toString())
        }
    }

    class ViewHolder(itemView: View, listener: onItemClickListner) :
        RecyclerView.ViewHolder(itemView) {
        var itemTitle: TextView
        var itemMark: TextView

        init {
            itemTitle = itemView.findViewById(R.id.student_title_tv)
            itemMark = itemView.findViewById(R.id.student_mark_tv)

            itemView.findViewById<CardView>(R.id.card_view).setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }
    }
}