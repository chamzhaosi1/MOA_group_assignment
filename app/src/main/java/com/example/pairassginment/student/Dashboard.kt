package com.example.pairassginment.student

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.pairassginment.databinding.ActivityDashboardBinding
import com.example.pairassginment.student.objectClass.BatchDeadline
import com.example.pairassginment.student.objectClass.StudentDetail
import com.google.firebase.firestore.FirebaseFirestore
import com.transferwise.sequencelayout.SequenceStep

class Dashboard : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding

    private var mDB: FirebaseFirestore = FirebaseFirestore.getInstance()

    private var student_detail: StudentDetail = StudentDetail()
    private var batch_deadline: BatchDeadline = BatchDeadline()

    val firstStep = binding.firstStep
    val secondStep = binding.secondStep
    val thirdStep = binding.thirdStep
    val fourthStep = binding.fourthStep
    val fifthStep = binding.fifthStep
    val sixthStep = binding.sixthStep
    val seventhStep = binding.seventhStep

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
    private var other_ducument_name_array: ArrayList<String>? = null;
    private var other_squence_step_array: ArrayList<SequenceStep>? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

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

        other_ducument_name_array = ArrayList();
        other_ducument_name_array!!.add("Proposal_PPT")
        other_ducument_name_array!!.add("Proposal")
        other_ducument_name_array!!.add("Final_Draft")
        other_ducument_name_array!!.add("Final_PPT")
        other_ducument_name_array!!.add("Final_Thesis")
        other_ducument_name_array!!.add("Poster")

        other_squence_step_array = ArrayList();
        other_squence_step_array!!.add(secondStep)
        other_squence_step_array!!.add(thirdStep)
        other_squence_step_array!!.add(fourthStep)
        other_squence_step_array!!.add(fifthStep)
        other_squence_step_array!!.add(sixthStep)
        other_squence_step_array!!.add(seventhStep)

        getStudentDetail()
    }

    private fun getStudentNameIDReady(){
        binding.studentNameIdTv.text = student_detail.student_name.toString() + " " + student_detail.student_id.toString()
    }

    private fun getSequenceStepReady(){
        Log.d("Batch Detail", student_detail.toString())

        mDB.collection("Batch")
            .document(student_detail.batch_id!!)
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

        getTopicSubmissionDetail()
    }

    private fun getTopicSubmissionDetail(){
        if(student_detail.submission_id != null){
            mDB.collection("Submission")
                .document(student_detail.submission_id!!)
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

                    val intentDetailList = Intent(this@Dashboard, ListOfThreeTopic::class.java)
                    val intentSubmitForm = Intent(this@Dashboard, TopicsSubmitForm::class.java)

                    if(topic_size > 0){
                        firstStep!!.setSubtitle("DEADLINE: " + batch_deadline.topics_deadline + "\n\n" + "STATUS: "+topic_status+ "\n\n" + "SUBMITTED: " + topic_size.toString() + "/3" )

                        if(topic_status == "Approved"){
                            topics_detail_btn?.visibility = View.VISIBLE
                            topics_submit_btn?.visibility = View.GONE

                            topics_detail_btn!!.setOnClickListener {
                                intentDetailList.putExtra("student_detail", student_detail)
                                intentDetailList.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                                startActivity(intentDetailList)
                            }

                            firstStep!!.setActive(false)
                            secondStep!!.setActive(true)
                            getProposalPPTSubmissionDetail()
                            Log.d("second step", secondStep.toString())

                        }else{
                            topics_detail_btn?.visibility = View.VISIBLE
                            topics_submit_btn?.visibility = View.VISIBLE

                            topics_detail_btn!!.setOnClickListener {
                                intentDetailList.putExtra("student_detail", student_detail)
                                startActivity(intentDetailList)
                            }

                            topics_submit_btn!!.setOnClickListener {
                                intentSubmitForm.putExtra("student_detail", student_detail)
                                startActivity(intentSubmitForm)
                            }
                        }
                    }
                }
        }else{
            topics_submit_btn!!.setOnClickListener {
                val intentSubmitForm = Intent(this@Dashboard, TopicsSubmitForm::class.java)
                intentSubmitForm.putExtra("student_detail", student_detail)
                startActivity(intentSubmitForm)

                val throwable = Throwable()
                throwable.printStackTrace()
            }
        }
    }

    private fun getProposalPPTSubmissionDetail(){

        do{
            mDB.collection("Submission")
                .document(student_detail.submission_id!!)
                .collection("Proposal_PPT")
                .get()
                .addOnSuccessListener { documents ->
                    Log.d("Proposal Submission Detail", documents.size().toString())
                    if (documents.size() > 0){
                        val status_array:ArrayList<String> = ArrayList()
                        var proposal_ppt_status:String? = null;
                        val proposal_ppt_size:Int = documents.size()
                        Log.d("Proposal Submission Detail", proposal_ppt_size.toString())

                        for(document in documents){
                            Log.d("Proposal Submission Detail", document.data["Status"].toString())
                            status_array!!.add(document.data["Status"].toString())
                        }

                        proposal_ppt_status = if(status_array!!.contains("Approved")){
                                                    "Approved"
                                                }else if (status_array!!.contains("Pending")){
                                                    "Pending"
                                                }else{
                                                    "Rejected"
                                                }

                        val intentDetailList = Intent(this@Dashboard, ListOfOtherDocuments::class.java)
                        val intentSubmitForm = Intent(this@Dashboard, OtherSubmitForm::class.java)

                        secondStep!!.setSubtitle("DEADLINE: " + batch_deadline.proposal_ppt_dealine + "\n\n" + "STATUS: "+proposal_ppt_status+ "\n\n" + "SUBMITTED: " + proposal_ppt_size.toString())

                        if(proposal_ppt_status == "Approved"){
                            proposal_ppt_detail_btn?.visibility = View.VISIBLE
                            proposal_ppt_submit_btn?.visibility = View.GONE

                            proposal_ppt_detail_btn!!.setOnClickListener {
                                intentDetailList.putExtra("student_detail", student_detail)
                                startActivity(intentDetailList)
                            }

                            secondStep!!.setActive(false)
                            thirdStep!!.setActive(true)



                        }else{
                            proposal_ppt_detail_btn?.visibility = View.VISIBLE
                            proposal_ppt_submit_btn?.visibility = View.VISIBLE

                            proposal_ppt_detail_btn!!.setOnClickListener {
                                intentDetailList.putExtra("student_detail", student_detail)
                                startActivity(intentDetailList)
                            }

                            proposal_ppt_submit_btn!!.setOnClickListener {
                                intentSubmitForm.putExtra("student_detail", student_detail)
                                startActivity(intentSubmitForm)
                            }
                        }

                    }else {
                        proposal_ppt_submit_btn?.visibility = View.VISIBLE

                        proposal_ppt_submit_btn!!.setOnClickListener {
                            val intentSubmitForm = Intent(this@Dashboard, OtherSubmitForm::class.java)
                            intentSubmitForm.putExtra("student_detail", student_detail)
                            startActivity(intentSubmitForm)
                        }
                    }
                }
        }while (hasContinue)
    }

    private fun getStudentDetail(){
        student_detail.role_id = intent.getStringExtra("role_id").toString()
        Log.d("Student detail", "Above"+student_detail.role_id!!)

        mDB.collection("Students")
            .document(student_detail.role_id!!)
            .get()
            .addOnCompleteListener {
                Log.d("Student detail", "run completed")
            }
            .addOnSuccessListener { document ->
                Log.d("Student detail", ""+document.data.toString())
                if (document != null){
                    Log.d("Student detail", "Success to retrieve student detail")
                    Log.d("Student detail", document.data.toString())

                    student_detail.student_name = document.data?.get("Name").toString()
                    student_detail.student_id = document.data?.get("Student_ID").toString()

                    val dataMap : MutableMap<String, Any>? = document.data
//                    Log.d("dataMap", dataMap.toString())

                    if(dataMap!!.containsKey("Batch_ID")){
                        student_detail.batch_id = dataMap["Batch_ID"].toString()
                    }

                    if(dataMap!!.containsKey("Mark_ID")){
                        student_detail.mark_id = dataMap["Mark_ID"].toString()
                    }

                    if(dataMap!!.containsKey("Submission_ID")){
                        student_detail.submission_id = dataMap["Submission_ID"].toString()
                    }

                    getStudentNameIDReady()
                    getSequenceStepReady()

                }
            }.addOnFailureListener{
                Log.d("Student detail", "Failure to retrieve student detail")
            }
    }
}