package com.example.pairassginment.supervisor

import android.annotation.SuppressLint
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
import com.example.pairassginment.supervisor.`object`.StudentSubmission
import com.example.pairassginment.supervisor.`object`.otherDocument
import com.example.pairassginment.supervisor.`object`.topic
import com.example.pairassginment.supervisor.recycleAdapter.SupervisorAdapter
import com.google.firebase.firestore.FirebaseFirestore

class Home : Fragment(), SupervisorAdapter.OnItemClickListener {
    private var itemsArray: ArrayList<HomeItems> = ArrayList();
    private var mDB: FirebaseFirestore = FirebaseFirestore.getInstance()
    private var studentSubmissionArray: ArrayList<StudentSubmission>? = null
    private var recyclerView : RecyclerView?= null
    private var adapter: SupervisorAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
//        getItems()


        recyclerView = view.findViewById<RecyclerView>(R.id.home_RV)
        recyclerView!!.layoutManager = LinearLayoutManager(context)

        getTopicsDetail()
        adapter = SupervisorAdapter(itemsArray, this)
        recyclerView!!.adapter = adapter

        return view
    }

    private fun getTopicsDetail() {
        val topicArray : ArrayList<topic> = ArrayList()
        val proposalPPTOtherDocumentArray : ArrayList<otherDocument> = ArrayList()
        val proposalOtherDocumentArray : ArrayList<otherDocument> = ArrayList()
        val finalDraftOtherDocumentArray : ArrayList<otherDocument> = ArrayList()
        val finalPPTOtherDocumentArray : ArrayList<otherDocument> = ArrayList()
        val finalThesisOtherDocumentArray : ArrayList<otherDocument> = ArrayList()
        val posterOtherDocumentArray : ArrayList<otherDocument> = ArrayList()

        val studentName: ArrayList<String> = ArrayList()
        val studentID: ArrayList<String> = ArrayList()

        studentSubmissionArray = ArrayList()
        val submission_id_a: ArrayList<String>? = ArrayList()

        val submission_collection = mDB.collection("Submission")

        mDB.collection("Students").get().addOnSuccessListener { documents ->
            if (documents.size() > 0) {
                for (document in documents) {
                    submission_id_a!!.add(document.get("Submission_ID").toString())
                }
            }
        }.continueWith{
            if(submission_id_a != null && submission_id_a.size > 0){
                for (index in submission_id_a.indices){
                    mDB.collection("Students")
                        .get()
                        .addOnSuccessListener { documents ->
                            studentName.clear()
                            studentID.clear()
                            for(document in documents){
                                studentName.add(document.get("Name").toString())
                                studentID.add(document.get("Student_ID").toString())
                            }
                        }.continueWith {
                            submission_collection
                                .document(submission_id_a[index])
                                .collection("Topics")
                                .get()
                                .addOnSuccessListener { documents ->
                                    if(documents.size() > 0){
                                        for (document in documents){
                                            topicArray.add(topic(
                                                title = document.get("Title").toString(),
                                                abstract = document.get("Abstract").toString(),
                                                dateSubmission = document.get("Date_Submit").toString(),
                                                status = document.get("Status").toString()
                                            ))
                                        }
                                        val temp : ArrayList<topic> = ArrayList()
                                        temp.addAll(topicArray)
                                        studentSubmissionArray!!.add(
                                            StudentSubmission(
                                                Topics = temp
                                            )
                                        )

                                        topicArray.clear()
                                    }
                                }.continueWith{
                                    submission_collection
                                        .document(submission_id_a[index])
                                        .collection("Proposal_PPT")
                                        .get()
                                        .addOnSuccessListener { documents ->
                                            if(documents.size() > 0){
                                                for (document in documents){
                                                    proposalPPTOtherDocumentArray.add(otherDocument(
                                                        dataSubmission = document.get("Date_Submit").toString(),
                                                        fileSubmission = document.get("File_Submitted").toString(),
                                                        fileSubmissionOrg = document.get("File_Submitted_Org").toString(),
                                                        status = document.get("Status").toString(),
                                                        studComment = document.get("Student_Comment").toString()
                                                    ))
                                                }
                                                val temp : ArrayList<otherDocument> = ArrayList()
                                                temp.addAll(proposalPPTOtherDocumentArray)
                                                studentSubmissionArray!![index].Proposal_PPT = temp

                                                proposalPPTOtherDocumentArray.clear()
                                            }
                                        }.continueWith{
                                            submission_collection
                                                .document(submission_id_a[index])
                                                .collection("Proposal")
                                                .get()
                                                .addOnSuccessListener { documents ->
                                                    if(documents.size() > 0){
                                                        for (document in documents){
                                                            proposalOtherDocumentArray.add(otherDocument(
                                                                dataSubmission = document.get("Date_Submit").toString(),
                                                                fileSubmission = document.get("File_Submitted").toString(),
                                                                fileSubmissionOrg = document.get("File_Submitted_Org").toString(),
                                                                status = document.get("Status").toString(),
                                                                studComment = document.get("Student_Comment").toString()
                                                            ))
                                                        }
                                                        val temp : ArrayList<otherDocument> = ArrayList()
                                                        temp.addAll(proposalOtherDocumentArray)
                                                        studentSubmissionArray!![index].Proposal = temp

                                                        proposalOtherDocumentArray.clear()
                                                    }
                                                }.continueWith{
                                                    submission_collection
                                                        .document(submission_id_a[index])
                                                        .collection("Final_Draft")
                                                        .get()
                                                        .addOnSuccessListener { documents ->
                                                            if(documents.size() > 0){
                                                                for (document in documents){
                                                                    finalDraftOtherDocumentArray.add(otherDocument(
                                                                        dataSubmission = document.get("Date_Submit").toString(),
                                                                        fileSubmission = document.get("File_Submitted").toString(),
                                                                        fileSubmissionOrg = document.get("File_Submitted_Org").toString(),
                                                                        status = document.get("Status").toString(),
                                                                        studComment = document.get("Student_Comment").toString()
                                                                    ))
                                                                }
                                                                val temp : ArrayList<otherDocument> = ArrayList()
                                                                temp.addAll(finalDraftOtherDocumentArray)
                                                                studentSubmissionArray!![index].Final_Draft = temp

                                                                finalDraftOtherDocumentArray.clear()
                                                            }
                                                        }.continueWith{
                                                            submission_collection
                                                                .document(submission_id_a[index])
                                                                .collection("Final_PPT")
                                                                .get()
                                                                .addOnSuccessListener { documents ->
                                                                    if(documents.size() > 0){
                                                                        for (document in documents){
                                                                            finalPPTOtherDocumentArray.add(otherDocument(
                                                                                dataSubmission = document.get("Date_Submit").toString(),
                                                                                fileSubmission = document.get("File_Submitted").toString(),
                                                                                fileSubmissionOrg = document.get("File_Submitted_Org").toString(),
                                                                                status = document.get("Status").toString(),
                                                                                studComment = document.get("Student_Comment").toString()
                                                                            ))
                                                                        }
                                                                        val temp : ArrayList<otherDocument> = ArrayList()
                                                                        temp.addAll(finalPPTOtherDocumentArray)
                                                                        studentSubmissionArray!![index].Final_PPT = temp

                                                                        finalPPTOtherDocumentArray.clear()
                                                                    }
                                                                }.continueWith {
                                                                    submission_collection
                                                                        .document(submission_id_a[index])
                                                                        .collection("Final_Thesis")
                                                                        .get()
                                                                        .addOnSuccessListener { documents ->
                                                                            if(documents.size() > 0){
                                                                                for (document in documents){
                                                                                    finalThesisOtherDocumentArray.add(otherDocument(
                                                                                        dataSubmission = document.get("Date_Submit").toString(),
                                                                                        fileSubmission = document.get("File_Submitted").toString(),
                                                                                        fileSubmissionOrg = document.get("File_Submitted_Org").toString(),
                                                                                        status = document.get("Status").toString(),
                                                                                        studComment = document.get("Student_Comment").toString()
                                                                                    ))
                                                                                }
                                                                                val temp : ArrayList<otherDocument> = ArrayList()
                                                                                temp.addAll(finalThesisOtherDocumentArray)
                                                                                studentSubmissionArray!![index].Final_Thesis = temp

                                                                                finalThesisOtherDocumentArray.clear()
                                                                            }
                                                                        }.continueWith{
                                                                            submission_collection
                                                                                .document(submission_id_a[index])
                                                                                .collection("Poster")
                                                                                .get()
                                                                                .addOnSuccessListener { documents ->
                                                                                    if(documents.size() > 0){
                                                                                        for (document in documents){
                                                                                            posterOtherDocumentArray.add(otherDocument(
                                                                                                dataSubmission = document.get("Date_Submit").toString(),
                                                                                                fileSubmission = document.get("File_Submitted").toString(),
                                                                                                fileSubmissionOrg = document.get("File_Submitted_Org").toString(),
                                                                                                status = document.get("Status").toString(),
                                                                                                studComment = document.get("Student_Comment").toString()
                                                                                            ))
                                                                                        }
                                                                                        val temp : ArrayList<otherDocument> = ArrayList()
                                                                                        temp.addAll(posterOtherDocumentArray)
                                                                                        studentSubmissionArray!![index].Poster = temp

                                                                                        posterOtherDocumentArray.clear()

                                                                                    }
                                                                                }.continueWith {
                                                                                    val num = studentName.size
                                                                                    for(i in studentName.indices){
                                                                                        if(studentID.size == num && studentSubmissionArray!!.size == num){
                                                                                            studentSubmissionArray!![i].studName = studentName[i]
                                                                                            studentSubmissionArray!![i].studID = studentID[i]
                                                                                        }
                                                                                        Log.d("adfsda7", studentSubmissionArray.toString())
                                                                                        getItems()
                                                                                    }
                                                                                }
                                                                        }
                                                                }
                                                        }
                                                }
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

    @SuppressLint("NotifyDataSetChanged")
    fun getItems() {
        itemsArray.clear()
        for (studentSubmit in studentSubmissionArray!!){
            itemsArray.add(HomeItems(studentSubmit.studName, studentSubmit?.Topics?.get(0)!!.status))
        }
        Log.d("itemsArray", itemsArray.toString())
        recyclerView!!.adapter!!.notifyDataSetChanged()

//        itemsArray.add(HomeItems("Cham zhao si", "PENDING"))
//        itemsArray.add(HomeItems("Lee Wei Heng", "APPROVED"))
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.frame_layout, fragment)
        fragmentTransaction?.commit()
    }
}