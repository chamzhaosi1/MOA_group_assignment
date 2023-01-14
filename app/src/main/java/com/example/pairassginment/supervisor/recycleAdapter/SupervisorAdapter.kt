package com.example.pairassginment.supervisor.recycleAdapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.pairassginment.R
import com.example.pairassginment.supervisor.`object`.HomeItems

class SupervisorAdapter(val items: ArrayList<HomeItems>, val listener: OnItemClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.supervisor_dashboard_recycle_view_row, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("dfasdf", items.toString())
        if (holder is ViewHolder) {
            when (items[position].homeStatus) {
                "APPROVED" -> {
                    holder.sup_dashboard_CL.setBackgroundColor(Color.parseColor("#57e655"))
                    holder.itemImage.setImageResource(R.drawable.ic_baseline_check_24)
                }
                "REJECTED" -> {
                    holder.sup_dashboard_CL.setBackgroundColor(Color.parseColor("#cc4343"))
                    holder.itemImage.setImageResource(R.drawable.ic_baseline_close_24)
                }
                "PENDING" -> {
                    holder.sup_dashboard_CL.setBackgroundColor(Color.parseColor("#ede65c"))
                    holder.itemImage.setImageResource(com.google.android.material.R.drawable.ic_clock_black_24dp)
                }

            }

            holder.itemTitle.setText(items[position].homeTitle)
        }
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var sup_dashboard_CL : ConstraintLayout
        var itemImage: ImageView
        var itemTitle: TextView

        init {
            sup_dashboard_CL = itemView.findViewById(R.id.sup_dashboard_CL)
            itemImage = itemView.findViewById(R.id.status_img)
            itemTitle = itemView.findViewById(R.id.student_title_tv)

            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }


}