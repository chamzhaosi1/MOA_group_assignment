package com.example.pairassginment.student

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pairassginment.databinding.ActivityListOfItemBinding
import com.example.pairassginment.student.objectClass.ThreeTopicsItem

class ListOfThreeTopic : AppCompatActivity() {
    private lateinit var binding: ActivityListOfItemBinding
    private lateinit var itemsArray: ArrayList<ThreeTopicsItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListOfItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        itemsArray = ArrayList();
        itemsArray.add(ThreeTopicsItem("Arduino of solar", "31 Dec 2022", "1 Jan 2023","", "Approved"))
        itemsArray.add(ThreeTopicsItem("Student Portal Website", "31 Dec 2022", "", "","Pending"))
        itemsArray.add(ThreeTopicsItem("Financing System", "31 Dec 2022","", "1 Jan 2023","Rejected"))
        itemsArray.add(ThreeTopicsItem("Arduino of solar", "31 Dec 2022", "1 Jan 2023","", "Approved"))
        itemsArray.add(ThreeTopicsItem("Student Portal Website", "31 Dec 2022", "", "","Pending"))
        itemsArray.add(ThreeTopicsItem("Financing System", "31 Dec 2022","", "1 Jan 2023","Rejected"))
        itemsArray.add(ThreeTopicsItem("Arduino of solar", "31 Dec 2022", "1 Jan 2023","", "Approved"))
        itemsArray.add(ThreeTopicsItem("Student Portal Website", "31 Dec 2022", "", "","Pending"))
        itemsArray.add(ThreeTopicsItem("Financing System", "31 Dec 2022","", "1 Jan 2023","Rejected"))
        itemsArray.add(ThreeTopicsItem("Arduino of solar", "31 Dec 2022", "1 Jan 2023","", "Approved"))
        itemsArray.add(ThreeTopicsItem("Student Portal Website", "31 Dec 2022", "", "","Pending"))
        itemsArray.add(ThreeTopicsItem("Financing System", "31 Dec 2022","", "1 Jan 2023","Rejected"))

        addItemsListIntoAdapter(itemsArray);

        binding.floatingHomeBtn.setOnClickListener{
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent);
        }
    }

    fun addItemsListIntoAdapter(itemsArray : ArrayList<ThreeTopicsItem>){
        // set linear layout manager
        val layoutManager = LinearLayoutManager(this)
        binding.listOfItemRecycleView.layoutManager = layoutManager

        // add the items list into layout
        val adpater = itemRecycleAdapter(this, itemsArray)
        binding.listOfItemRecycleView.adapter = adpater

        // set each card listener
        adpater.setOnClickListener(object : itemRecycleAdapter.onItemClickListner{
            override fun onItemClick(position: Int) {
                // To do some things, that you want
                Toast.makeText(this@ListOfThreeTopic, "Topic Clicked: " + itemsArray[position].topicSubmitted, Toast.LENGTH_SHORT).show()
            }
        })
    }
}