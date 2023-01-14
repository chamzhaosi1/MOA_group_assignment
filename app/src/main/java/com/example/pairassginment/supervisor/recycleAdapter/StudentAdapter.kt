package com.example.pairassginment.supervisor.recycleAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pairassginment.R
import com.example.pairassginment.supervisor.`object`.Student

class StudentAdapter(val items: ArrayList<Student>, val listener: OnItemClickListener) :

    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.supervisor_student_list_row, parent, false)
            return ViewHolder(itemView)
        }

        override fun getItemCount(): Int {
            return items.size
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            if (holder is ViewHolder) {
                holder.pptImage.setImageResource(R.drawable.ppt)
                holder.proposalImage.setImageResource(R.drawable.txt)
                holder.thesisImage.setImageResource(R.drawable.thesis)
                holder.itemTitle.setText(items[position].studentName)
            }
        }

        inner class ViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView), View.OnClickListener {
            var pptImage: ImageView
            var proposalImage: ImageView
            var thesisImage: ImageView
            var itemTitle: TextView

            init {
                pptImage = itemView.findViewById(R.id.ppt_iv)
                proposalImage = itemView.findViewById(R.id.proposal_iv)
                thesisImage = itemView.findViewById(R.id.thesis_iv)
                itemTitle = itemView.findViewById(R.id.student_title_tv)
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