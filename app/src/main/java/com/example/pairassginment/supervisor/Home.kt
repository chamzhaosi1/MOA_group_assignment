package com.example.pairassginment.supervisor

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pairassginment.R
import com.example.pairassginment.databinding.FragmentHomeBinding
import com.example.pairassginment.student.itemRecycleAdapter
import com.example.pairassginment.student.objectClass.StudentDetail
import com.example.pairassginment.student.objectClass.ThreeTopicsItem
import com.example.pairassginment.supervisor.`object`.HomeItems
import com.example.pairassginment.supervisor.`object`.otherDocument
import com.example.pairassginment.supervisor.`object`.topic
import com.example.pairassginment.supervisor.recycleAdapter.SupervisorAdapter
import com.google.firebase.firestore.FirebaseFirestore

class Home : Fragment(), SupervisorAdapter.OnItemClickListener {
    private lateinit var itemsArray: ArrayList<HomeItems>
    private var mDB: FirebaseFirestore = FirebaseFirestore.getInstance()
    private var student_detail: StudentDetail? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        itemsArray = ArrayList();

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        getItems()

        view.findViewById<RecyclerView>(R.id.home_RV).layoutManager = LinearLayoutManager(context)
        view.findViewById<RecyclerView>(R.id.home_RV).adapter = SupervisorAdapter(itemsArray, this)

        return view
    }

    private fun getTopicsDetail() {
        val topicArray : ArrayList<topic> = ArrayList()
        val otherDocumentArray : ArrayList<otherDocument> = ArrayList()
        mDB.collection("Students").get().addOnSuccessListener { documents ->
            if (documents.size() > 0) {
                for (document in documents) {
                    val submissionID : String? = document.get("Submission_ID").toString()
                    val submissionCollection = mDB.collection("Submission").document(submissionID!!)
                    submissionCollection.collection("Topics").get().addOnSuccessListener { documents ->
                        if (documents.size() > 0) {
                            for (document in documents) {
                               val topic : topic = topic(document.get("Title").toString(), document.get("Abstract").toString(), document.get("Date_Submit").toString(), document.get("Status").toString())
                                topicArray.add(topic)
                            }
                        }
                    }
                    submissionCollection.collection("Proposal_PPT").get().addOnSuccessListener { documents ->
                        if (documents.size() > 0) {
                            for (document in documents) {
                                val otherDocument : otherDocument = otherDocument(document.get("Date_Submit").toString(), document.get("File_Submitted").toString(), document.get("File_Submitted_Org").toString(), document.get("Status").toString(), document.get("Student_Comment").toString())
                                otherDocumentArray.add(otherDocument)
                            }
                        }
                    }
                    submissionCollection.collection("Proposal").get().addOnSuccessListener { documents ->
                        if (documents.size() > 0) {
                            for (document in documents) {
                                val otherDocument : otherDocument = otherDocument(document.get("Date_Submit").toString(), document.get("File_Submitted").toString(), document.get("File_Submitted_Org").toString(), document.get("Status").toString(), document.get("Student_Comment").toString())
                                otherDocumentArray.add(otherDocument)
                            }
                        }
                    }
                    submissionCollection.collection("Poster").get().addOnSuccessListener { documents ->
                        if (documents.size() > 0) {
                            for (document in documents) {
                                val otherDocument : otherDocument = otherDocument(document.get("Date_Submit").toString(), document.get("File_Submitted").toString(), document.get("File_Submitted_Org").toString(), document.get("Status").toString(), document.get("Student_Comment").toString())
                                otherDocumentArray.add(otherDocument)
                            }
                        }
                    }
                    submissionCollection.collection("Final_Thesis").get().addOnSuccessListener { documents ->
                        if (documents.size() > 0) {
                            for (document in documents) {
                                val otherDocument : otherDocument = otherDocument(document.get("Date_Submit").toString(), document.get("File_Submitted").toString(), document.get("File_Submitted_Org").toString(), document.get("Status").toString(), document.get("Student_Comment").toString())
                                otherDocumentArray.add(otherDocument)
                            }
                        }
                    }
                    submissionCollection.collection("Final_PPT").get().addOnSuccessListener { documents ->
                        if (documents.size() > 0) {
                            for (document in documents) {
                                val otherDocument : otherDocument = otherDocument(document.get("Date_Submit").toString(), document.get("File_Submitted").toString(), document.get("File_Submitted_Org").toString(), document.get("Status").toString(), document.get("Student_Comment").toString())
                                otherDocumentArray.add(otherDocument)
                            }
                        }
                    }
                    submissionCollection.collection("Final_Draft").get().addOnSuccessListener { documents ->
                        if (documents.size() > 0) {
                            for (document in documents) {
                                val otherDocument : otherDocument = otherDocument(document.get("Date_Submit").toString(), document.get("File_Submitted").toString(), document.get("File_Submitted_Org").toString(), document.get("Status").toString(), document.get("Student_Comment").toString())
                                otherDocumentArray.add(otherDocument)
                            }
                        }
                    }
                }
            }
        }


    }

    override fun onItemClick(position: Int) {
        val type = itemsArray[position].homeTitle.toString()

        when (type) {
            "Cham Zhao Si" -> replaceFragment(StudentWork())
            "Lee Wei Heng" -> replaceFragment(StudentWork())
        }
    }

    fun getItems() {
        itemsArray = ArrayList();

        itemsArray.add(HomeItems("Cham Zhao Si", "PENDING"))
        itemsArray.add(HomeItems("Lee Wei Heng", "APPROVED"))


    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.frame_layout, fragment)
        fragmentTransaction?.commit()
    }
}