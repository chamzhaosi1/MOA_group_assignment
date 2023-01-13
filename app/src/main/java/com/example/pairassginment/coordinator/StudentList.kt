package com.example.pairassginment.coordinator

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pairassginment.R
import com.example.pairassginment.coordinator.adapter.CoordinatorAdapter
import com.example.pairassginment.coordinator.objectClass.StudentData


class StudentList : Fragment() {
    private var itemsArray: ArrayList<StudentData> = ArrayList()
    private var MY_ITEM_CODE_REQUEST: Int = 10;

    val startItemForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        onItemActivityResult(MY_ITEM_CODE_REQUEST, result)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("CutPasteId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_student_list, container, false)
        getItems()
//        val intent_view_approve_mark= Intent(context, ViewAndApproveMark::class.java)
        val layoutManager = LinearLayoutManager(context)
        val adapter = CoordinatorAdapter(itemsArray)
        val recyclerViewLayoutManager = view.findViewById<RecyclerView>(R.id.recyclerView2)
        val recyclerViewAdapter = view.findViewById<RecyclerView>(R.id.recyclerView2)

        Log.d("asd2", itemsArray.toString())

        recyclerViewLayoutManager.layoutManager = layoutManager
        recyclerViewAdapter.adapter= adapter

        val intent_view_all_mark = Intent(this.context, ViewAllMark::class.java)

        // set each card listener
        adapter.setOnClickListener(object : CoordinatorAdapter.onItemClickListner {
            override fun onItemClick(position: Int) {
                intent_view_all_mark.putExtra("item_click", itemsArray[position])
                startItemForResult.launch(intent_view_all_mark)
            }
        })

        return view
    }

    // once the activity is finished than get the result
    private fun onItemActivityResult(requestCode: Int, result: ActivityResult){
        if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
            when (requestCode) {
                MY_ITEM_CODE_REQUEST -> {
                    Toast.makeText(context, intent!!.getStringExtra("message"), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun getItems(){
        itemsArray = ArrayList();
        itemsArray.add(StudentData("Lee Wei Heng", 60, 5, 3,4,5,5,5,5,5,3,2,3,5,5, 5))
        itemsArray.add(StudentData("Cham Zhao si", 50,3, 3,2,3,3,3,5,5,3,2,3,5,5, 5))
        itemsArray.add(StudentData("Chia Yue Shyang", 60, 5, 3,4,5,5,5,5,5,3,2,3,5,5, 5))
        itemsArray.add(StudentData("Tan Zhen Xun", 40, 1, 3,4,1,1,1,1,1,1,2,1,1,1, 1))
        itemsArray.add(StudentData("Wong Jing Yi", 5, 5))
        itemsArray.add(StudentData("Lee Wei Heng", 60, 5, 3,4,5,5,5,5,5,3,2,3,5,5, 5))
        itemsArray.add(StudentData("Cham Zhao si", 50,3, 3,2,3,3,3,5,5,3,2,3,5,5, 5))
        itemsArray.add(StudentData("Chia Yue Shyang", 60, 5, 3,4,5,5,5,5,5,3,2,3,5,5, 5))
        itemsArray.add(StudentData("Tan Zhen Xun", 40, 1, 3,4,1,1,1,1,1,1,2,1,1,1, 1))
        itemsArray.add(StudentData("Wong Jing Yi", 5, 5))
        itemsArray.add(StudentData("Lee Wei Heng", 60, 5, 3,4,5,5,5,5,5,3,2,3,5,5, 5))
        itemsArray.add(StudentData("Cham Zhao si", 50,3, 3,2,3,3,3,5,5,3,2,3,5,5, 5))
        itemsArray.add(StudentData("Chia Yue Shyang", 60, 5, 3,4,5,5,5,5,5,3,2,3,5,5, 5))
        itemsArray.add(StudentData("Tan Zhen Xun", 40, 1, 3,4,1,1,1,1,1,1,2,1,1,1, 1))
        itemsArray.add(StudentData("Wong Jing Yi", 5, 5))

    }

}