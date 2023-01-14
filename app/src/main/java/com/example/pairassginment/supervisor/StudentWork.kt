package com.example.pairassginment.supervisor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pairassginment.R
import com.example.pairassginment.supervisor.`object`.StudWorkClass
import com.example.pairassginment.supervisor.recycleAdapter.StudWorkAdapter


class StudentWork : Fragment(), StudWorkAdapter.OnItemClickListener {

    private lateinit var itemsArray: ArrayList<StudWorkClass>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_student_work, container, false)
        getItems()

        view.findViewById<RecyclerView>(R.id.studWork_rv).layoutManager =
            LinearLayoutManager(context)
        view.findViewById<RecyclerView>(R.id.studWork_rv).adapter =
            StudWorkAdapter(itemsArray, this)

        return view
    }

    override fun onItemClick(position: Int) {
        val type = itemsArray[position].itemImage.toString()

        when (type) {
            "Proposal_PPT" -> replaceFragment(pptApprove())
            "Proposal" -> replaceFragment(GiveMarkAndApprove())
            "Final_Thesis" -> replaceFragment(ThesisApproveAndMark())
            else -> replaceFragment(TitleApprove())
        }
    }

    fun getItems() {
        itemsArray = ArrayList();

        itemsArray.add(StudWorkClass("bhj", "Final_Thesis", "10/60", "2023-1-1", "2023-1-2", "PENDING"))
        itemsArray.add(StudWorkClass("bhj", "Proposal_PPT", "10/60", "2023-1-1", "2023-1-2", "PENDING"))
        itemsArray.add(StudWorkClass("bhj", "Proposal", "10/60", "2023-1-1", "2023-1-2", "APPROVED"))
        itemsArray.add(StudWorkClass("bhj", "Poster", "10/60", "2023-1-1", "2023-1-2", "REJECTED"))
        itemsArray.add(StudWorkClass("Title1", "Final_Thesis", "10/60", "2023-1-1", "2023-1-2","APPROVED"))
        itemsArray.add(StudWorkClass("Title1", "Proposal_PPT", "10/60", "2023-1-1", "2023-1-2","PENDING"))
        itemsArray.add(StudWorkClass("Title1", "Proposal", "10/60", "2023-1-1", "2023-1-2","APPROVED"))
        itemsArray.add(StudWorkClass("Title1", "Poster", "10/60", "2023-1-1", "2023-1-2","PENDING"))
        itemsArray.add(StudWorkClass("Title1", "idea", "0/0", "2023-1-3", "2023-1-4","APPROVED"))
        itemsArray.add(StudWorkClass("bhjnnn", "idea", "0/0", "2023-1-2", "2023-1-5", "REJECTED"))
//        itemsArray.add(StudWorkClass("bhj", "idea", "0/0", "2023-1-2", "2023-1-5","APPROVED"))
        itemsArray.add(StudWorkClass("hjj", "idea", "0/0", "2023-1-1", "2023-1-6", "REJECTED"))

    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.frame_layout, fragment)
        fragmentTransaction?.commit()
    }

}
