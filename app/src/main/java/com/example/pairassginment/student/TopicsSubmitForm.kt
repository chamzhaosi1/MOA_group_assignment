package com.example.pairassginment.student

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.pairassginment.R
import com.example.pairassginment.databinding.ActivityTopicsSubmitFormBinding

class TopicsSubmitForm : AppCompatActivity() {
    private lateinit var binding: ActivityTopicsSubmitFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTopicsSubmitFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.submitBtn.setOnClickListener{
            when (submitOperation()){
                true -> {
                    val intent = Intent()
                    intent.putExtra("message", "submit successfully")
                    setResult(Activity.RESULT_OK, intent)
                    finish();
                }

                false -> {
                    Toast.makeText(this, "All filed must be fill up, empty is not allowed!", Toast.LENGTH_SHORT).show();
                }
            }
        }

        binding.backBtn.setOnClickListener {
            val intent = Intent(this, ListOfThreeTopic::class.java)
            startActivity(intent)
        }
    }

    // whatever success or fail, pass a message to notice
    private fun submitOperation(): Boolean{
        Log.d("TAG", "title: "+binding.topicTitleEt.text.toString().trim());
        Log.d("TAG", "abstract: "+binding.topicTitleEt.text.toString().trim());
        if(binding.topicTitleEt.text.toString().trim().isEmpty() ||
                binding.abstractsEt.text.toString().trim().isEmpty()){
            return false
        }
        return true
    }
}