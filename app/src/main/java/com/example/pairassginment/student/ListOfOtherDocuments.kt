package com.example.pairassginment.student

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pairassginment.R
import com.example.pairassginment.databinding.ActivityListOfItemBinding

class ListOfOtherDocuments : AppCompatActivity() {
    private lateinit var binding: ActivityListOfItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListOfItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}