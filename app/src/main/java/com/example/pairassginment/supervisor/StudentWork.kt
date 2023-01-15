package com.example.pairassginment.supervisor

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pairassginment.R
import com.example.pairassginment.supervisor.`object`.StudWorkClass
import com.example.pairassginment.supervisor.`object`.StudentSubmission
import com.example.pairassginment.supervisor.`object`.otherDocument
import com.example.pairassginment.supervisor.recycleAdapter.StudWorkAdapter


class StudentWork : Fragment(), StudWorkAdapter.OnItemClickListener {
    private var recyclerView: RecyclerView? = null
    private var title: String? = null
    val allItems = ArrayList<Any>()
    var itemsArray: StudentSubmission? = null

    //    private lateinit var itemsArray: ArrayList<StudWorkClass>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_student_work, container, false)
//        getItems()
        itemsArray = arguments?.getParcelable("item clicked")
        view.findViewById<TextView>(R.id.textView5).text = itemsArray!!.studName +" "+ itemsArray!!.studID

        Log.d("item_clicked", itemsArray.toString())
        recyclerView = view.findViewById<RecyclerView>(R.id.studWork_rv)
        recyclerView!!.layoutManager = LinearLayoutManager(context)


        if(itemsArray!!.Topics != null && itemsArray!!.Topics!!.size > 0){
            for(item in itemsArray!!.Topics!!){
                allItems.add(item)
                if (item.status == "APPROVED") {
                    title = item.title
                }
            }
        }

        if(itemsArray!!.Proposal_PPT != null && itemsArray!!.Proposal_PPT!!.size > 0){
            for(item in itemsArray!!.Proposal_PPT!!){
                allItems.add(item)
            }
        }

        if(itemsArray!!.Proposal != null && itemsArray!!.Proposal!!.size > 0){
            for(item in itemsArray!!.Proposal!!){
                allItems.add(item)
            }
        }

        if(itemsArray!!.Final_Draft != null && itemsArray!!.Final_Draft!!.size > 0){
            for(item in itemsArray!!.Final_Draft!!){
                allItems.add(item)
            }
        }

        if(itemsArray!!.Final_PPT != null && itemsArray!!.Final_PPT!!.size > 0){
            for(item in itemsArray!!.Final_PPT!!){
                allItems.add(item)
            }
        }

        if(itemsArray!!.Final_Thesis != null && itemsArray!!.Final_Thesis!!.size > 0){
            for(item in itemsArray!!.Final_Thesis!!){
                allItems.add(item)
            }
        }

        if(itemsArray!!.Poster != null && itemsArray!!.Poster!!.size > 0){
            for(item in itemsArray!!.Poster!!){
                allItems.add(item)
            }
        }

        Log.d("afads", allItems.toString())
        recyclerView!!.adapter = StudWorkAdapter(allItems, this)

        return view
    }

    override fun onItemClick(position: Int) {
//        val type = itemsArray[position].homeTitle.toString()
        val bundle = Bundle()
        val giveMarkAndApprove = GiveMarkAndApprove()
        bundle.putString( "title", title)

        Log.d("jhgSub", itemsArray?.submission_ID.toString())

        bundle.putString( "submission_ID", itemsArray?.submission_ID)
        bundle.putString( "mark_ID", itemsArray?.mark_ID)
        bundle.putString( "stud_ID", itemsArray?.stud_ID)
        bundle.putParcelable("item clicked", allItems?.get(position) as otherDocument)
        giveMarkAndApprove.arguments = bundle
        replaceFragment(giveMarkAndApprove)
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.frame_layout, fragment)
        fragmentTransaction?.commit()
    }

}
