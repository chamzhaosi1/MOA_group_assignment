package com.example.pairassginment.student

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.pairassginment.databinding.ActivityDashboardBinding
import com.example.pairassginment.student.objectClass.BatchDeadline
import com.example.pairassginment.student.objectClass.StudentDetail
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ServerTimestamp
import com.transferwise.sequencelayout.SequenceStep


class Dashboard : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding

    private var mDB: FirebaseFirestore = FirebaseFirestore.getInstance()

    private var student_detial: StudentDetail = StudentDetail()
    private var batch_deadline: BatchDeadline = BatchDeadline()

    private var firstStep: SequenceStep? = null;
    private var secondStep: SequenceStep? = null;
    private var thirdStep: SequenceStep? = null;
    private var fourthStep: SequenceStep? = null;
    private var fifthStep: SequenceStep? = null;
    private var sixthStep: SequenceStep? = null;
    private var seventhStep: SequenceStep? = null;

    private var topics_submit_btn: Button? = null;
    private var topics_detail_btn: Button? = null;
    private var proposal_ppt_submit_btn: Button? = null;
    private var proposal_ppt_detail_btn: Button? = null;
    private var proposal_submit_btn: Button? = null;
    private var proposal_detail_btn: Button? = null;
    private var final_draft_submit_btn: Button? = null;
    private var final_draft_detail_btn: Button? = null;
    private var final_ppt_submit_btn: Button? = null;
    private var final_ppt_detial_btn: Button? = null;
    private var final_thesis_submit_btn: Button? = null;
    private var final_thesis_detail_btn: Button? = null;
    private var poster_submit_btn: Button? = null;
    private var poster_detial_btn: Button? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        firstStep = binding.firstStep
        secondStep = binding.secondStep
        thirdStep = binding.thirdStep
        fourthStep = binding.fourthStep
        fifthStep = binding.fifthStep
        sixthStep = binding.sixthStep
        seventhStep = binding.seventhStep

        topics_submit_btn = binding.topicsSubmitBtn
        topics_detail_btn = binding.topicsDetailBtn
        proposal_ppt_submit_btn = binding.proposalPptSlideSubmitBtn
        proposal_ppt_detail_btn = binding.proposalPptSlideDetailBtn
        proposal_submit_btn = binding.proposalSubmitBtn
        proposal_detail_btn = binding.proposalDetailBtn
        final_draft_submit_btn = binding.finalDraftSubmitBtn
        final_draft_detail_btn = binding.finalDraftDetailBtn
        final_ppt_submit_btn = binding.finalPptSlideSubmitBtn
        final_ppt_detial_btn = binding.finalPptSlideDetailBtn
        final_thesis_submit_btn = binding.finalThesisSubmitBtn
        final_thesis_detail_btn = binding.finalThesisDetailBtn
        poster_submit_btn = binding.posterSubmitBtn
        poster_detial_btn = binding.posterDetailBtn

        getStudentDetail()

    }

    private fun getStudentNameIDReady(){
        binding.studentNameIdTv.text = student_detial.student_name.toString() + " " + student_detial.student_id.toString()
    }

    private fun getSequenceStepReady(){
        Log.d("Batch Detail", student_detial.toString())

        mDB.collection("Batch")
            .document(student_detial.batch_id!!)
            .get()
            .addOnCompleteListener{
                Log.d("Batch Detail", "Run complete")
            }
            .addOnSuccessListener { document ->
                Log.d("Batch Detail", ""+document.data)
                if(document != null){
                    batch_deadline.intake_mnt_year = document.data?.get("Intake_MntYear").toString();

                    batch_deadline.topics_begin = document.data?.get("Topics_Begin").toString();
                    batch_deadline.proposal_ppt_begin = document.data?.get("Proposal_PPT_Begin").toString();
                    batch_deadline.proposal_begin = document.data?.get("Proposal_Begin").toString();
                    batch_deadline.final_draft_begin = document.data?.get("Final_Draft_Begin").toString();
                    batch_deadline.final_ppt_begin = document.data?.get("Final_PPT_Begin").toString();
                    batch_deadline.final_thesis_begin = document.data?.get("Final_Thesis_Begin").toString();
                    batch_deadline.poster_begin = document.data?.get("Poster_Begin").toString();

                    batch_deadline.topics_deadline = document.data?.get("Topics_Deadline").toString();
                    batch_deadline.proposal_ppt_dealine = document.data?.get("Proposal_PPT_Deadline").toString();
                    batch_deadline.proposal_deadline = document.data?.get("Proposal_Deadline").toString();
                    batch_deadline.final_draft_deadline = document.data?.get("Final_Draft_Deadline").toString();
                    batch_deadline.final_ppt_deadline = document.data?.get("Final_PPT_Deadline").toString();
                    batch_deadline.final_thesis_deadline = document.data?.get("Final_Thesis_Deadline").toString();
                    batch_deadline.poster_deadline = document.data?.get("Poster_Deadline").toString();

                    setSequenceStepUI()
                }
            }
    }

    private fun setSequenceStepUI(){
        firstStep!!.setActive(true)
        firstStep!!.setAnchor(batch_deadline.topics_begin)
        firstStep!!.setSubtitle("DEADLINE: " + batch_deadline.topics_deadline + "\n\n" + "STATUS: WAITING" + "\n\n" + "SUBMITTED: 0/3" )

//        secondStep!!.setActive(false)
        secondStep!!.setAnchor(batch_deadline.proposal_ppt_begin)
        secondStep!!.setSubtitle("DEADLINE: " + batch_deadline.proposal_ppt_dealine + "\n\n" + "STATUS: WAITING" + "\n\n" + "SUBMITTED: 0" )

//        thirdStep!!.setActive(false)
        thirdStep!!.setAnchor(batch_deadline.proposal_begin)
        thirdStep!!.setSubtitle("DEADLINE: " + batch_deadline.proposal_deadline + "\n\n" + "STATUS: WAITING" + "\n\n" + "SUBMITTED: 0" )

//        fourthStep!!.setActive(false)
        fourthStep!!.setAnchor(batch_deadline.final_draft_begin)
        fourthStep!!.setSubtitle("DEADLINE: " + batch_deadline.final_draft_deadline + "\n\n" + "STATUS: WAITING" + "\n\n" + "SUBMITTED: 0" )

//        fifthStep!!.setActive(false)
        fifthStep!!.setAnchor(batch_deadline.final_ppt_begin)
        fifthStep!!.setSubtitle("DEADLINE: " + batch_deadline.final_ppt_deadline + "\n\n" + "STATUS: WAITING" + "\n\n" + "SUBMITTED: 0" )

//        sixthStep!!.setActive(false)
        sixthStep!!.setAnchor(batch_deadline.final_thesis_begin)
        sixthStep!!.setSubtitle("DEADLINE: " + batch_deadline.final_thesis_deadline + "\n\n" + "STATUS: WAITING" + "\n\n" + "SUBMITTED: 0" )

//        seventhStep!!.setActive(false)
        seventhStep!!.setAnchor(batch_deadline.poster_begin)
        seventhStep!!.setSubtitle("DEADLINE: " + batch_deadline.poster_deadline + "\n\n" + "STATUS: WAITING" + "\n\n" + "SUBMITTED: 0" )


        topics_submit_btn?.visibility = View.VISIBLE
        topics_detail_btn?.visibility = View.GONE
        proposal_ppt_submit_btn?.visibility = View.GONE
        proposal_ppt_detail_btn?.visibility = View.GONE
        proposal_submit_btn?.visibility = View.GONE
        proposal_detail_btn?.visibility = View.GONE
        final_draft_submit_btn?.visibility = View.GONE
        final_draft_detail_btn?.visibility = View.GONE
        final_ppt_submit_btn?.visibility = View.GONE
        final_ppt_detial_btn?.visibility = View.GONE
        final_thesis_submit_btn?.visibility = View.GONE
        final_thesis_detail_btn?.visibility = View.GONE
        poster_submit_btn?.visibility = View.GONE
        poster_detial_btn?.visibility = View.GONE

        getSubmissionDetail()
    }

    private fun getSubmissionDetail(){
        if(student_detial.submission_id!!.isNotEmpty()){
            mDB.collection("Submission")
                .document(student_detial.submission_id!!)
                .collection("Topics")
                .get()
                .addOnSuccessListener { documents ->
                    val status_array:ArrayList<String> = ArrayList()
                    var topic_status:String? = null;
                    val topic_size:Int = documents.size()
                    Log.d("Submission Detail", topic_size.toString())

                    for(document in documents){
                        Log.d("Submission Detail", document.data["Status"].toString())
                        status_array!!.add(document.data["Status"].toString())
                    }

                    topic_status = if(status_array!!.contains("Approved")){
                                    "Approved"
                                }else if (status_array!!.contains("Pending")){
                                    "Pending"
                                }else{
                                    "Rejected"
                                }

                    if(topic_size > 0){
                        firstStep!!.setSubtitle("DEADLINE: " + batch_deadline.topics_deadline + "\n\n" + "STATUS: "+topic_status+ "\n\n" + "SUBMITTED: " + topic_size.toString() + "/3" )

                        if(topic_status == "Approved"){
                            topics_detail_btn?.visibility = View.VISIBLE
                            topics_submit_btn?.visibility = View.GONE
                            firstStep!!.setActive(false)
                            secondStep!!.setActive(true)
                            Log.d("second step", secondStep.toString())
                        }else{
                            topics_detail_btn?.visibility = View.VISIBLE
                            topics_submit_btn?.visibility = View.VISIBLE
                        }
                    }
                }
        }
    }

    private fun getStudentDetail(){
        student_detial.role_id = intent.getStringExtra("role_id").toString()
        Log.d("Student detail", "Above"+student_detial.role_id!!)

        mDB.collection("Students")
            .document(student_detial.role_id!!)
            .get()
            .addOnCompleteListener {
                Log.d("Student detail", "run completed")
            }
            .addOnSuccessListener { document ->
                Log.d("Student detail", ""+document.data.toString())
                if (document != null){
                    Log.d("Student detail", "Success to retrieve student detail")
                    Log.d("Student detail", document.data.toString())

                    student_detial.student_name = document.data?.get("Name").toString()
                    student_detial.student_id = document.data?.get("Student_ID").toString()

                    val dataMap : MutableMap<String, Any>? = document.data
//                    Log.d("dataMap", dataMap.toString())

                    if(dataMap!!.containsKey("Batch_ID")){
                        student_detial.batch_id = dataMap["Batch_ID"].toString()
                    }

                    if(dataMap!!.containsKey("Mark_ID")){
                        student_detial.mark_id = dataMap["Mark_ID"].toString()
                    }

                    if(dataMap!!.containsKey("Submission_ID")){
                        student_detial.submission_id = dataMap["Submission_ID"].toString()
                    }

                    getStudentNameIDReady()
                    getSequenceStepReady()

                }
            }.addOnFailureListener{
                Log.d("Student detail", "Failure to retrieve student detail")
            }
    }
}