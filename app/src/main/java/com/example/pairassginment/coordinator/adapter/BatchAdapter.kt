package com.example.pairassginment.coordinator.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.pairassginment.R
import com.example.pairassginment.coordinator.objectClass.BatchData
import com.example.pairassginment.coordinator.objectClass.StudentData
import com.example.pairassginment.student.itemRecycleAdapter

class BatchAdapter(val items: ArrayList<BatchData>) :
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
            .inflate(R.layout.coordinator_batch_recycle_view_row, parent, false)
        return ViewHolder(itemView, mListener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("item listener", items.toString())
        if (holder is ViewHolder) {
            holder.batchName.setText(items[position].intake_mnt_year.toString())
        }
    }

    class ViewHolder(itemView: View, listener: onItemClickListner) :
        RecyclerView.ViewHolder(itemView) {
        var batchName: TextView

        init {
            batchName = itemView.findViewById(R.id.batch_name)

            itemView.findViewById<CardView>(R.id.card_view).setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }
    }
}