package com.example.pairassginment.student

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pairassginment.R
import com.example.pairassginment.databinding.ActivityDashboardBinding
import com.example.pairassginment.databinding.ActivityListOfItemBinding
import com.example.pairassginment.recycleAdapter.itemRecycleAdapter

class ListOfItem : AppCompatActivity() {
    private lateinit var binding: ActivityListOfItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListOfItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.listOfItemRecycleView.layoutManager = layoutManager

        val testArray = ArrayList<three_topic_item>()
        testArray.add(three_topic_item("Arduino of solar", "31 Dec 2022", "1 Jan 2023"))

        val adpater = itemRecycleAdapter(this, testArray)
        binding.listOfItemRecycleView.adapter = adpater


        binding.floatingHomeBtn.setOnClickListener{
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent);
        }
    }
}