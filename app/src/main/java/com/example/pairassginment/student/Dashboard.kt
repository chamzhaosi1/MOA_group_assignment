package com.example.pairassginment.student

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.pairassginment.R
import com.example.pairassginment.databinding.ActivityDashboardBinding
import com.example.pairassginment.student.objectClass.StudentDetail
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.transferwise.sequencelayout.SequenceStep
import java.util.Objects

class Dashboard : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    private var mDB: FirebaseFirestore = FirebaseFirestore.getInstance()
    private var mAuth: FirebaseAuth.AuthStateListener? = null
    private var userID: String? = null
    private var student_detial: StudentDetail = StudentDetail()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getStudentDetail()

    }

    private fun getStudentDetail(){
        student_detial.role_id = intent.getStringExtra("role_id").toString()

        mDB.collection("Students")
            .document(student_detial.role_id!!)
            .get()
            .addOnSuccessListener { document ->
                if (document != null){
                    Log.d("Student detail", document.data.toString())

                    student_detial.student_name = document.data?.get("Name").toString()
                    student_detial.student_id = document.data?.get("Student_ID").toString()

//                    Log.d("Babi", document.data?.get("Babi").toString())
                    if(document.data?.get("Babi").toString().isNullOrBlank()){
                        Log.d("Babi", "testing is Null or Blank")
                    }else(
                            Log.d("Babi", "testing is not Null or Blank")
                    )

                    student_detial.batch_id = document.data?.get("Batch_ID").toString()
                    student_detial.mark_id = document.data?.get("Mark_ID").toString()
                    student_detial.submission_id = document.data?.get("Submission_ID").toString()
                }

//                Log.d("Test", student_detial.toString())
            }
    }
}