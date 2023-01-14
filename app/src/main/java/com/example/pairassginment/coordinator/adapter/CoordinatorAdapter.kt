package com.example.pairassginment.coordinator.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.pairassginment.R
import com.example.pairassginment.coordinator.objectClass.StudentData

import android.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat

class CoordinatorAdapter(val items: ArrayList<StudentData>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var mListener : onItemClickListner

    interface onItemClickListner{
        fun onItemClick(position: Int)
    }

    fun setOnClickListener(listener: onItemClickListner){
        Log.d("listener", items.toString())
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

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("item listener", items.toString())

        if (holder is ViewHolder) {
            Log.d("Color", items[position].status.toString())
            when(items[position].status){
                "Approved" -> holder.cardView.setBackgroundColor(Color.parseColor("#57e655"))
                "Revised" -> holder.cardView.setBackgroundColor(Color.parseColor("#63b3d6"))
                "Pending" -> holder.cardView.setBackgroundColor(Color.parseColor("#ede65c"))
                else -> holder.cardView.setBackgroundColor(Color.parseColor("#adada5"))
            }

            holder.itemTitle.setText(items[position].name)
            holder.itemMark.setText(items[position].total_mark.toString())
        }
    }

    class ViewHolder(itemView: View, listener: onItemClickListner) :
        RecyclerView.ViewHolder(itemView) {
        var itemTitle: TextView
        var itemMark: TextView
        var cardView: ConstraintLayout

        init {
            itemTitle = itemView.findViewById(R.id.student_title_tv)
            itemMark = itemView.findViewById(R.id.student_mark_tv)
            cardView = itemView.findViewById(R.id.card_view_constraint)

            itemView.findViewById<CardView>(R.id.card_view).setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }
    }
}