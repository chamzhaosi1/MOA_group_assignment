package com.example.pairassginment.student

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.pairassginment.R
import com.example.pairassginment.databinding.ActivityDashboardBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.transferwise.sequencelayout.SequenceStep
import java.util.Objects

class Dashboard : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    private var mDB: FirebaseFirestore = FirebaseFirestore.getInstance();

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        val step1 = binding.firstStep as SequenceStep
//        val step3 = binding.thirdStep as SequenceStep
//
//        //programatically activating
//        step3.setActive(true)
//        step3.setTitle("Active Step")
//        step3.setAnchor("Date...")
//        step3.setSubtitle("Subtitle of this step third.")
//
//        //programatically seting style to Title
//        step3.setTitleTextAppearance(R.style.vertical_progress_bar_title)
//
//        binding.detialBtn.setOnClickListener{
//            var intent = Intent(this, ListOfThreeTopic::class.java)
//            startActivity(intent)
//        }

        val uid:String = FirebaseAuth.getInstance().currentUser!!.uid;
        val email:String = FirebaseAuth.getInstance().currentUser!!.email.toString();
        Log.d("User id", uid)

        val user = hashMapOf<String, Any>(
            "email" to email,
            "name" to "Cham Zhao Si",
            "address" to "30-11 Jalan Daud",
            "gender" to "mela"
        )
//
        mDB.collection("Users")
            .document(uid)
            .update(user)
            .addOnCompleteListener {
                Toast.makeText(this@Dashboard, "record added successfully", Toast.LENGTH_SHORT).show()
            }

//        mDB.collection("Users")
//            .whereEqualTo("name", "Cham Zhao Si")
//            .get()
//            .addOnSuccessListener { documents ->
//                for(document in documents){
//                    if (document != null){
//                        Log.d("Result", document.data.toString())
//                    }
//                }
//            }

    }
}