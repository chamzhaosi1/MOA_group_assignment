package com.example.pairassginment.coordinator

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pairassginment.R
import com.example.pairassginment.coordinator.adapter.BatchAdapter
import com.example.pairassginment.coordinator.adapter.CoordinatorAdapter
import com.example.pairassginment.coordinator.objectClass.BatchData
import com.example.pairassginment.coordinator.objectClass.StudentData
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore

class BeginAndDeadline : Fragment() {
    private var itemsArray: ArrayList<BatchData> = ArrayList()
    private var recyclerViewAdapter: RecyclerView? = null
    private var adapter: BatchAdapter? = null
    private var addBtn: FloatingActionButton? = null
    private var MY_ITEM_CODE_REQUEST: Int = 10;
    private var mDB: FirebaseFirestore = FirebaseFirestore.getInstance()

    val startItemForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        onItemActivityResult(MY_ITEM_CODE_REQUEST, result)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("CutPasteId", "MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_begin_and_deadline, container, false)
        getItemsDetail()

        addBtn = view.findViewById(R.id.floating_add_btn)

        val layoutManager = LinearLayoutManager(context)
        val recyclerViewLayoutManager = view.findViewById<RecyclerView>(R.id.batch_name_recycle_view)
        recyclerViewLayoutManager.layoutManager = layoutManager

        adapter = BatchAdapter(itemsArray)
        recyclerViewAdapter = view.findViewById(R.id.batch_name_recycle_view)
        recyclerViewAdapter!!.adapter= adapter

        // set each card listener
        setOnItemClickListener()
        setFloatingAddBtn()

        return view
    }

    private fun setFloatingAddBtn(){
        val intent = Intent(this.context, SaveViewBatch::class.java)

        addBtn!!.setOnClickListener{
            startActivity(intent)
        }
    }

    private fun setOnItemClickListener(){
        val intent_view_batch = Intent(this.context, SaveViewBatch::class.java)

        adapter!!.setOnClickListener(object : BatchAdapter.onItemClickListner {
            override fun onItemClick(position: Int) {
                Log.d("item click", itemsArray[position].toString())
                intent_view_batch.putExtra("item_click", itemsArray[position])
                startItemForResult.launch(intent_view_batch)
            }
        })
    }

    // once the activity is finished than get the result
    private fun onItemActivityResult(requestCode: Int, result: ActivityResult){
        if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
            when (requestCode) {
                MY_ITEM_CODE_REQUEST -> {
                    val message = "Approved Successfully"
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun getItemsDetail(){
        mDB.collection("Batch")
            .get()
            .addOnSuccessListener { documents ->
                Log.d("addffasfsad", documents.size().toString())
                if(documents.size() > 0){
                    for(document in documents){
                        if (document != null){
                            Log.d("item detail", document.get("Intake_MntYear").toString())
                            itemsArray.add(BatchData(
                                document.id,
                                document.get("Intake_MntYear").toString(),
                                document.get("Topics_Begin").toString(),
                                document.get("Proposal_PPT_Begin").toString(),
                                document.get("Proposal_Begin").toString(),
                                document.get("Final_Draft_Begin").toString(),
                                document.get("Final_PPT_Begin").toString(),
                                document.get("Final_Thesis_Begin").toString(),
                                document.get("Poster_Begin").toString(),
                                document.get("Topics_Deadline").toString(),
                                document.get("Proposal_PPT_Deadline").toString(),
                                document.get("Proposal_Deadline").toString(),
                                document.get("Final_Draft_Deadline").toString(),
                                document.get("Final_PPT_Deadline").toString(),
                                document.get("Final_Thesis_Deadline").toString(),
                                document.get("Poster_Deadline").toString(),
                            ))
                        }
                    }
                    adapter!!.notifyDataSetChanged()

                }else{
                    Log.d("addffasfsad", "afdasdfadsf")
                    val intent = Intent(this.context, SaveViewBatch::class.java)
                    startActivity(intent)
                }
            }
    }
}