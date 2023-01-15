package com.example.pairassginment.supervisor.recycleAdapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.pairassginment.R
import com.example.pairassginment.student.ListOfOtherDocuments
import com.example.pairassginment.student.objectClass.OtherDocumentItem
import com.example.pairassginment.supervisor.StudentWork
import com.example.pairassginment.supervisor.`object`.StudWorkClass
import com.example.pairassginment.supervisor.`object`.StudentSubmission
import com.example.pairassginment.supervisor.`object`.otherDocument
import com.example.pairassginment.supervisor.`object`.topic
import com.google.common.collect.ArrayTable
import com.google.firebase.database.collection.LLRBNode.Color

class StudWorkAdapter(val allItems: ArrayList<Any>? = null, val listener: OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.supervisor_single_student_row, parent, false)

        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return allItems!!.size
//        return if(otherDocuments != null){
//            Log.d("otherDocuments2", otherDocuments!!.size.toString())
//            otherDocuments.size
//        }else{
//            Log.d("otherDocuments1", topicDocument!!.size.toString())
//            topicDocument!!.size
//        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {

            if (allItems!!.size > 0){
                Log.d("fadsfasd-1", allItems[position].toString())
                val item: Any? = when (allItems[position]) {
                    is topic -> allItems[position]
                    else -> allItems[position] as? otherDocument
                }
                Log.d("fadsfasd0", item.toString())
                if(item is topic){
                    Log.d("fadsfasd1", item.toString())
                    when(item.documentType){
                        "Proposal_PPT"-> holder.image.setImageResource(R.drawable.ppt)
                        "Proposal"-> holder.image.setImageResource(R.drawable.txt)
                        "Final_PPT"-> holder.image.setImageResource(R.drawable.ppt)
                        "Final_Draft"-> holder.image.setImageResource(R.drawable.txt)
                        "Final_Thesis" -> holder.image.setImageResource(R.drawable.thesis)
                        "Poster" -> holder.image.setImageResource(R.drawable.brochure)
                        else -> holder.image.setImageResource(R.drawable.idea)
                    }
                    when(item.status) {
                        "APPROVED" ->holder.card_view1.setCardBackgroundColor(android.graphics.Color.parseColor("#57e655"))
                        "REJECTED" ->holder.card_view1.setCardBackgroundColor(android.graphics.Color.parseColor("#cc4343"))
                        "PENDING" ->holder.card_view1.setCardBackgroundColor(android.graphics.Color.parseColor("#ede65c"))
                        else -> holder.card_view1.setBackgroundColor(android.graphics.Color.parseColor("#adada5"))
                    }

                    holder.topic.text = item.title
                    holder.subDate.text = item.dateSubmission

                    if(item.dateFeedback != "null"){
                        holder.approveRejectDate.text = item.dateFeedback
                    }else{
                        holder.approveRejectDate.visibility = View.GONE
                        holder.approveTv.visibility = View.GONE
                    }

                }else if(item is otherDocument){
                    Log.d("fadsfasd2", item.toString())
                    when(item.documentType){
                        "Proposal_PPT"-> holder.image.setImageResource(R.drawable.ppt)
                        "Proposal"-> holder.image.setImageResource(R.drawable.txt)
                        "Final_PPT"-> holder.image.setImageResource(R.drawable.ppt)
                        "Final_Draft"-> holder.image.setImageResource(R.drawable.txt)
                        "Final_Thesis" -> holder.image.setImageResource(R.drawable.thesis)
                        "Poster" -> holder.image.setImageResource(R.drawable.brochure)
                        else -> holder.image.setImageResource(R.drawable.idea)
                    }
                    when(item.status) {
                        "APPROVED" ->holder.card_view1.setCardBackgroundColor(android.graphics.Color.parseColor("#57e655"))
                        "REJECTED" ->holder.card_view1.setCardBackgroundColor(android.graphics.Color.parseColor("#cc4343"))
                        "PENDING" ->holder.card_view1.setCardBackgroundColor(android.graphics.Color.parseColor("#ede65c"))
                        else -> holder.card_view1.setBackgroundColor(android.graphics.Color.parseColor("#adada5"))
                    }

//                    holder.topic.text = items.title
                    holder.topic.visibility = View.GONE
                    holder.subDate.text = item.dateSubmission

                    if(item.dateFeedback != "null"){
                        holder.approveRejectDate.text = item.dateFeedback
                    }else{
                        holder.approveRejectDate.visibility = View.GONE
                        holder.approveTv.visibility = View.GONE
                    }
                }

            }
        }
    }


    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var card_view1: CardView
        var image: ImageView
        var topic: TextView
        var subDate: TextView
        var approveRejectDate: TextView
        var approveTv: TextView

        init {
            card_view1 = itemView.findViewById(R.id.card_view)
            image = itemView.findViewById(R.id.item_image)
            topic = itemView.findViewById(R.id.itemTopicSubmitted)
            subDate = itemView.findViewById(R.id.item_submitted_date_tv)
            approveRejectDate = itemView.findViewById(R.id.item_approved_rejected_date_tv)
            approveTv = itemView.findViewById(R.id.textView8)
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
