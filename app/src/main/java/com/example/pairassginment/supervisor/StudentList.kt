package com.example.pairassginment.supervisor

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pairassginment.R
import com.example.pairassginment.supervisor.`object`.*
import com.example.pairassginment.supervisor.recycleAdapter.StudentAdapter
import com.example.pairassginment.supervisor.recycleAdapter.SupervisorAdapter
import com.google.firebase.firestore.FirebaseFirestore

class StudentList : Fragment(), StudentAdapter.OnItemClickListener {
    private var itemsArray: ArrayList<Student> = ArrayList();
    private var mDB: FirebaseFirestore = FirebaseFirestore.getInstance()
    private var studentSubmissionArray: ArrayList<StudentSubmission>? = null
    private var recyclerView : RecyclerView?= null
    private var adapter: StudentAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_student_list, container, false)
//        getItems()

        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView2)
        recyclerView!!.layoutManager = LinearLayoutManager(context)

        getTopicsDetail()
        adapter = StudentAdapter(itemsArray, this)
        recyclerView!!.adapter = adapter

        return view
    }

    override fun onItemClick(position: Int) {
        val bundle = Bundle()
        val studentWork = StudentWork()
        Log.d("kljSub", studentSubmissionArray?.get(position).toString())
        bundle.putParcelable("item clicked", studentSubmissionArray?.get(position))
        studentWork.arguments = bundle
        replaceFragment(studentWork)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun getItems() {
        itemsArray.clear()
        for (studentSubmit in studentSubmissionArray!!){
            itemsArray.add(Student(studentSubmit.studName))
        }
        Log.d("itemsArray", itemsArray.toString())
        recyclerView!!.adapter!!.notifyDataSetChanged()

//        itemsArray = ArrayList();
//        itemsArray.add(Student("Cham Zhao Si", "ppt", "thesis", "txt" ))
//        itemsArray.add(Student("Lee Wei Heng", "ppt", "thesis", "txt" ))

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
        val submission_ID: ArrayList<String> = ArrayList()
        val mark_ID: ArrayList<String> = ArrayList()
        val Stud_ID: ArrayList<String> = ArrayList()

        studentSubmissionArray = ArrayList()
        val submission_id_a: ArrayList<String>? = ArrayList()

        val submission_collection = mDB.collection("Submission")

        mDB.collection("Students").get().addOnSuccessListener { documents ->
            if (documents.size() > 0) {
                for (document in documents) {
                    submission_id_a!!.add(document.get("Submission_ID").toString())
                    submission_ID.add(document.get("Submission_ID").toString())
                    mark_ID.add(document.get("Mark_ID").toString())
                    Stud_ID.add(document.id)
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
                                            topicArray.add(
                                                topic(
                                                title = document.get("Title").toString(),
                                                abstract = document.get("Abstract").toString(),
                                                dateSubmission = document.get("Date_Submit").toString(),
                                                status = document.get("Status").toString(),
                                                documentType = "Topics",
                                                dateFeedback = document.get("Date_Feedback").toString(),
                                                    document_ID = document.id,
                                            )
                                            )
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
                                                    Log.d("dsdDco", document.id)
                                                    proposalPPTOtherDocumentArray.add(
                                                        otherDocument(
                                                        dateSubmission = document.get("Date_Submit").toString(),
                                                        fileSubmission = document.get("File_Submitted").toString(),
                                                        fileSubmissionOrg = document.get("File_Submitted_Org").toString(),
                                                        status = document.get("Status").toString(),
                                                        studComment = document.get("Student_Comment").toString(),
                                                        documentType = "Proposal_PPT",
                                                        dateFeedback = document.get("Date_Feedback").toString(),
                                                            document_ID = document.id,
                                                            supComment = document.get("Supervisor_Comment").toString(),
                                                    )
                                                    )
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
                                                            proposalOtherDocumentArray.add(
                                                                otherDocument(
                                                                dateSubmission = document.get("Date_Submit").toString(),
                                                                fileSubmission = document.get("File_Submitted").toString(),
                                                                fileSubmissionOrg = document.get("File_Submitted_Org").toString(),
                                                                status = document.get("Status").toString(),
                                                                studComment = document.get("Student_Comment").toString(),
                                                                documentType = "Proposal",
                                                                dateFeedback = document.get("Date_Feedback").toString(),
                                                                    document_ID = document.id,
                                                                    supComment = document.get("Supervisor_Comment").toString(),
                                                            )
                                                            )
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
                                                                    finalDraftOtherDocumentArray.add(
                                                                        otherDocument(
                                                                        dateSubmission = document.get("Date_Submit").toString(),
                                                                        fileSubmission = document.get("File_Submitted").toString(),
                                                                        fileSubmissionOrg = document.get("File_Submitted_Org").toString(),
                                                                        status = document.get("Status").toString(),
                                                                        studComment = document.get("Student_Comment").toString(),
                                                                        documentType = "Final_Draft",
                                                                        dateFeedback = document.get("Date_Feedback").toString(),
                                                                            document_ID = document.id,
                                                                            supComment = document.get("Supervisor_Comment").toString(),
                                                                    )
                                                                    )
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
                                                                            finalPPTOtherDocumentArray.add(
                                                                                otherDocument(
                                                                                dateSubmission = document.get("Date_Submit").toString(),
                                                                                fileSubmission = document.get("File_Submitted").toString(),
                                                                                fileSubmissionOrg = document.get("File_Submitted_Org").toString(),
                                                                                status = document.get("Status").toString(),
                                                                                studComment = document.get("Student_Comment").toString(),
                                                                                documentType = "Final_PPT",
                                                                                dateFeedback = document.get("Date_Feedback").toString(),
                                                                                    document_ID = document.id,
                                                                                    supComment = document.get("Supervisor_Comment").toString(),
                                                                            )
                                                                            )
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
                                                                                    finalThesisOtherDocumentArray.add(
                                                                                        otherDocument(
                                                                                        dateSubmission = document.get("Date_Submit").toString(),
                                                                                        fileSubmission = document.get("File_Submitted").toString(),
                                                                                        fileSubmissionOrg = document.get("File_Submitted_Org").toString(),
                                                                                        status = document.get("Status").toString(),
                                                                                        studComment = document.get("Student_Comment").toString(),
                                                                                        documentType = "Final_Thesis",
                                                                                        dateFeedback = document.get("Date_Feedback").toString(),
                                                                                            document_ID = document.id,
                                                                                            supComment = document.get("Supervisor_Comment").toString(),
                                                                                    )
                                                                                    )
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
                                                                                            posterOtherDocumentArray.add(
                                                                                                otherDocument(
                                                                                                dateSubmission = document.get("Date_Submit").toString(),
                                                                                                fileSubmission = document.get("File_Submitted").toString(),
                                                                                                fileSubmissionOrg = document.get("File_Submitted_Org").toString(),
                                                                                                status = document.get("Status").toString(),
                                                                                                studComment = document.get("Student_Comment").toString(),
                                                                                                documentType = "Poster",
                                                                                                dateFeedback = document.get("Date_Feedback").toString(),
                                                                                                    document_ID = document.id,
                                                                                                    supComment = document.get("Supervisor_Comment").toString(),
                                                                                            )
                                                                                            )
                                                                                        }
                                                                                        val temp : ArrayList<otherDocument> = ArrayList()
                                                                                        temp.addAll(posterOtherDocumentArray)
                                                                                        studentSubmissionArray!![index].Poster = temp

                                                                                        posterOtherDocumentArray.clear()

                                                                                    }
                                                                                }.continueWith {
                                                                                    val num = studentName.size
                                                                                    for(i in studentName.indices){
                                                                                        if(studentID.size == num && studentSubmissionArray!!.size == num && submission_ID.size == num && mark_ID.size == num && Stud_ID.size == num){
                                                                                            studentSubmissionArray!![i].studName = studentName[i]
                                                                                            studentSubmissionArray!![i].studID = studentID[i]
                                                                                            studentSubmissionArray!![i].submission_ID = submission_ID[i]
                                                                                            studentSubmissionArray!![i].mark_ID = mark_ID[i]
                                                                                            studentSubmissionArray!![i].stud_ID = Stud_ID[i]
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

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.frame_layout, fragment)
        fragmentTransaction?.commit()
    }
}