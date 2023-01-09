package com.example.pairassginment.student

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pairassginment.databinding.ActivityListOfItemBinding
import com.example.pairassginment.student.objectClass.OtherDucumentItem
import com.example.pairassginment.student.objectClass.StudentDetail
import com.example.pairassginment.student.objectClass.ThreeTopicsItem

class ListOfOtherDocuments : AppCompatActivity() {
    private lateinit var binding: ActivityListOfItemBinding
    private lateinit var itemsArray: ArrayList<OtherDucumentItem>
    private var MY_CODE_REQUEST: Int = 0;
    private var MY_ITEM_CODE_REQUEST: Int = 10;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListOfItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        itemsArray = ArrayList();
        itemsArray.add(OtherDucumentItem( "Arduino of solar", "31 Dec 2022", "1 Jan 2023","", "chamzhaosi_ppt_slide.ppt" ,"Approved", "bala, bala, bala ..."))
        itemsArray.add(OtherDucumentItem( "Student Portal Website", "31 Dec 2022", "", "","chamzhaosi_ppt_slide.ppt" ,"Pending", "bala, bala, bala ..."))
        itemsArray.add(OtherDucumentItem("Financing System", "31 Dec 2022","", "1 Jan 2023","chamzhaosi_ppt_slide.ppt" ,"Rejected", "bala, bala, bala ..."))
        itemsArray.add(OtherDucumentItem("Arduino of solar", "31 Dec 2022", "1 Jan 2023","","chamzhaosi_ppt_slide.ppt" , "Approved", "bala, bala, bala ..."))
        itemsArray.add(OtherDucumentItem("Student Portal Website", "31 Dec 2022", "", "","chamzhaosi_ppt_slide.ppt" ,"Pending", "bala, bala, bala ..."))
        itemsArray.add(OtherDucumentItem( "Financing System", "31 Dec 2022","", "1 Jan 2023","chamzhaosi_ppt_slide.ppt" ,"Rejected", "bala, bala, bala ..."))
        itemsArray.add(OtherDucumentItem( "Arduino of solar", "31 Dec 2022", "1 Jan 2023","","chamzhaosi_ppt_slide.ppt" , "Approved", "bala, bala, bala ..."))
        itemsArray.add(OtherDucumentItem( "Student Portal Website", "31 Dec 2022", "", "","chamzhaosi_ppt_slide.ppt" ,"Pending", "bala, bala, bala ..."))
        itemsArray.add(OtherDucumentItem("Financing System", "31 Dec 2022","", "1 Jan 2023","chamzhaosi_ppt_slide.ppt" ,"Rejected", "bala, bala, bala ..."))
        itemsArray.add(OtherDucumentItem("Arduino of solar", "31 Dec 2022", "1 Jan 2023","","chamzhaosi_ppt_slide.ppt" , "Approved", "bala, bala, bala ..."))
        itemsArray.add(OtherDucumentItem("Student Portal Website", "31 Dec 2022", "", "","chamzhaosi_ppt_slide.ppt" ,"Pending", "bala, bala, bala ..."))
        itemsArray.add(OtherDucumentItem("Financing System", "31 Dec 2022","", "1 Jan 2023","chamzhaosi_ppt_slide.ppt" ,"Rejected", "bala, bala, bala ..."))

        addItemsListIntoAdapter(itemsArray);

        val student_detail = intent.getParcelableExtra<StudentDetail>("student_detail")
        Log.d("Student detail list", student_detail.toString())

        // set home button listener
        binding.floatingHomeBtn.setOnClickListener{
            val intent = Intent(this, Dashboard::class.java)
            intent.putExtra("role_id", student_detail!!.role_id)
            startActivity(intent);
            finish();
        }

        // register an intent that will result a result
        val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            onActivityResult(MY_CODE_REQUEST, result)
        }

        // set create / add btn listener
        binding.floatingAddBtn.setOnClickListener{
            val intent = Intent(this, TopicsSubmitForm::class.java)
            // after registered and clicked the btn then get the intent launch
            startForResult.launch(intent)
        }
    }

    // once the activity is finished than get the result
    private fun onActivityResult(requestCode: Int, result: ActivityResult) {
        if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
            when (requestCode) {
                MY_CODE_REQUEST -> {
                    Toast.makeText(this, intent!!.getStringExtra("message"), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun addItemsListIntoAdapter(itemsArray : ArrayList<OtherDucumentItem>){
        // set linear layout manager
        val layoutManager = LinearLayoutManager(this)
        binding.listOfItemRecycleView.layoutManager = layoutManager

        // add the items list into layout
        val adapter = OtherDocumentAdapter(this, itemsArray)
        binding.listOfItemRecycleView.adapter = adapter

        val startItemForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            onItemActivityResult(MY_ITEM_CODE_REQUEST, result)
        }

        val intent_view_submit_form = Intent(this, ViewTopicsSubmitForm::class.java)
        val intent_topic_submit_form = Intent(this, TopicsSubmitForm::class.java)

        // set each card listener
        adapter.setOnClickListener(object : OtherDocumentAdapter.onItemClickListner{
            override fun onItemClick(position: Int) {
                // To do some things, that you want
                // Toast.makeText(this@ListOfThreeTopic, "Topic Clicked: " + itemsArray[position].topicSubmitted, Toast.LENGTH_SHORT).show(
                intent_view_submit_form.putExtra("item_clicked", itemsArray[position])
                intent_topic_submit_form.putExtra("item_clicked", itemsArray[position])

                when(itemsArray[position].submittedStatus){
                    "Pending"  -> startActivity(intent_topic_submit_form)
                    else -> startItemForResult.launch(intent_view_submit_form)
                }
            }
        })
    }

    // once the activity is finished than get the result
    private fun onItemActivityResult(requestCode: Int, result: ActivityResult){
        if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
            when (requestCode) {
                MY_CODE_REQUEST -> {
                    Toast.makeText(this, intent!!.getStringExtra("message"), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}