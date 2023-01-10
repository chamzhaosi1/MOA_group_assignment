package com.example.pairassginment.student

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.example.pairassginment.R
import com.example.pairassginment.databinding.ActivityTopicsSubmitFormBinding
import com.example.pairassginment.student.objectClass.StudentDetail
import com.example.pairassginment.student.objectClass.ThreeTopicsItem
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class TopicsSubmitForm : AppCompatActivity() {
    private lateinit var binding: ActivityTopicsSubmitFormBinding
    private var student_detail: StudentDetail? = null;
    private var item_topics_detail: ThreeTopicsItem? = null;
    private var topics_title_et: EditText? = null;
    private var abstract_et: EditText? =null;
    private var mDB: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTopicsSubmitFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        topics_title_et = binding.topicTitleEt
        abstract_et = binding.abstractsEt

        student_detail = intent.getParcelableExtra<StudentDetail>("student_detail")
        item_topics_detail = intent.getParcelableExtra<ThreeTopicsItem>("item_clicked")

        Log.d("student_detail", student_detail.toString())
        Log.d("item_topics detail", item_topics_detail.toString())

        getStudentNameIDReady()
        getTopicsItemReadyWhenPending()
        setBtnListner()
    }

    private fun getTopicsItemReadyWhenPending(){
        if (item_topics_detail != null){
            topics_title_et!!.setText(item_topics_detail!!.title.toString())
            abstract_et!!.setText(item_topics_detail!!.abstract.toString())
        }
    }

    private fun getStudentNameIDReady(){
        binding.studentNameIdTv.text = student_detail!!.student_name.toString() + " " + student_detail!!.student_id.toString()
    }

    private fun setBtnListner(){
        binding.submitBtn.setOnClickListener{
            Log.d("Submit check", submitOperation().toString())
            when (submitOperation()){
                true -> {
                    updateOrAddTopicsDataToDB()
                }
                false -> {
                    Toast.makeText(this, "All filed must be fill up, empty is not allowed!", Toast.LENGTH_SHORT).show();
                }
            }
        }

        binding.backBtn.setOnClickListener {
            val intent = Intent(this, ListOfThreeTopic::class.java)
            setResult(Activity.RESULT_OK, intent);
            finish()
        }
    }

    private fun updateOrAddTopicsDataToDB(){
        val title = binding.topicTitleEt.text.toString()
        val abstract = binding.abstractsEt.text.toString()
        val date_submit = SimpleDateFormat("dd MMM yyyy").format(Date())
        val topics_collection = mDB.collection("Submission")
                                    .document(student_detail!!.submission_id!!)
                                    .collection("Topics")
        Log.d("Update DB", item_topics_detail.toString())

        if(item_topics_detail != null){
            topics_collection
                .document(item_topics_detail!!.topic_id!!)
                .update("Title", title, "Abstract", abstract, "Data_Submit", date_submit)
                .addOnSuccessListener {
                    val intent = Intent()
                    intent.putExtra("student_detail", student_detail)
                    startActivity(intent)
                }
        }else {
            val topicData = hashMapOf<String, Any>(
                "Abstract" to abstract,
                "Date_Submit" to date_submit,
                "Title" to title,
                "Status" to "Pending"
            )

            topics_collection
                .add(topicData)
                .addOnSuccessListener { document ->
                    val document_id = document.id
                    Log.d("Document id", document_id.toString())
                    topics_collection
                        .document(document_id)
                        .update("Topics_ID", document_id)
                        .addOnSuccessListener {
                            val intent = Intent()
                            intent.putExtra("student_detail", student_detail)
                            startActivity(intent)
                        }
                }
        }
    }

    // whatever success or fail, pass a message to notice
    private fun submitOperation(): Boolean{
        Log.d("TAG", "title: "+binding.topicTitleEt.text.toString().trim());
        Log.d("TAG", "abstract: "+binding.abstractsEt.text.toString().trim());
        if(binding.topicTitleEt.text.toString().trim().isEmpty() ||
                binding.abstractsEt.text.toString().trim().isEmpty()){
            return false
        }
        return true
    }
}