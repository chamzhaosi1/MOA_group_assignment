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

class BeginAndDeadline : Fragment() {
    private var itemsArray: ArrayList<BatchData> = ArrayList()
    private var recyclerViewAdapter: RecyclerView? = null
    private var adapter: BatchAdapter? = null
    private var addBtn: FloatingActionButton? = null
    private var MY_ITEM_CODE_REQUEST: Int = 10;

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
        getItems()

        addBtn = view.findViewById(R.id.floating_add_btn)

        val layoutManager = LinearLayoutManager(context)
        val recyclerViewLayoutManager = view.findViewById<RecyclerView>(R.id.batch_name_recycle_view)
        recyclerViewLayoutManager.layoutManager = layoutManager

        adapter = BatchAdapter(itemsArray)
        recyclerViewAdapter = view.findViewById<RecyclerView>(R.id.batch_name_recycle_view)
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

    private fun getItems(){
        itemsArray = ArrayList();
        itemsArray.add(BatchData("11 May 2022", "01 Sep 2022", "01 Oct 2022","01 Nov 2022", "01 Dec 2022", "01 Jan 2023", "01 Feb 2023", "01 Mar 2023", "30 Sep 2022", "31 Oct 2022", "30 Nov 2022", "31 Dec 2022", "31 Jan 2023", "28 Feb 2023", "31 Mar 2023"))
        itemsArray.add(BatchData("11 Sep 2022", "01 Oct 2022", "01 Nov 2022","01 Dec 2022", "01 Jan 2023", "01 Feb 2023", "01 Mar 2023", "01 Sep 2023", "31 Oct 2022", "30 Nov 2022", "31 Dec 2022", "31 Jan 2023", "28 Feb 2023", "31 Mar 2023", "30 Apr 2023"))
        itemsArray.add(BatchData("11 Oct 2022", "01 Nov 2022", "01 Dec 2022","01 Jan 2023", "01 Feb 2023", "01 Mar 2023", "01 Apr 2023", "01 Oct 2023", "30 Nov 2022", "31 Dec 2022", "31 Jan 2023", "28 Feb 2023", "31 Mar 2023", "30 Apr 2023", "31 May 2023"))
        itemsArray.add(BatchData("11 Nov 2022", "01 Dec 2022", "01 Jan 2023","01 Feb 2023", "01 Mar 2023", "01 Apr 2023", "01 May 2023", "01 Nov 2023", "31 Dec 2022", "31 Jan 2023", "28 Feb 2023", "31 Mar 2023", "30 Apr 2023", "31 May 2023", "30 Jun 2023"))
        itemsArray.add(BatchData("11 Dec 2022", "01 Jan 2023", "01 Oct 2022","01 Mar 2023", "01 Apr 2023", "01 Mar 2023", "01 Jun 2023", "01 Dec 2023", "31 Jan 2023", "28 Feb 2023", "31 Mar 2023", "30 Apr 2023", "31 May 2023", "30 Jun 2023", "31 July 2023"))

    }

}