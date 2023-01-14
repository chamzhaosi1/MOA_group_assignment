package com.example.pairassginment.coordinator

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pairassginment.R
import com.example.pairassginment.coordinator.adapter.CoordinatorAdapter
import com.example.pairassginment.coordinator.objectClass.StudentData
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.awaitAll
import java.util.*
import kotlin.collections.ArrayList


class StudentList : Fragment() {
    private var itemsArray: ArrayList<StudentData> = ArrayList()
    private var tempArrayList: ArrayList<StudentData> = ArrayList()
    private var newArrayList: ArrayList<StudentData> = ArrayList()
    private var MY_ITEM_CODE_REQUEST: Int = 10;
    private var searchView: SearchView? = null;
    private var recyclerViewAdapter: RecyclerView? = null
    private var adapter: CoordinatorAdapter? = null
    private var mDB: FirebaseFirestore = FirebaseFirestore.getInstance()

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
        getStudentMarkDetail()

//        val intent_view_approve_mark= Intent(context, ViewAndApproveMark::class.java)
        val layoutManager = LinearLayoutManager(context)

        tempArrayList.addAll(itemsArray)

        adapter = CoordinatorAdapter(tempArrayList)
        val recyclerViewLayoutManager = view.findViewById<RecyclerView>(R.id.recyclerView2)
        recyclerViewAdapter = view.findViewById<RecyclerView>(R.id.recyclerView2)
        searchView = view.findViewById<SearchView>(R.id.searchView)

        searchView()

        Log.d("asd2", itemsArray.toString())

        recyclerViewLayoutManager.layoutManager = layoutManager
        recyclerViewAdapter!!.adapter= adapter

        // set each card listener
        setOnItemClickListener()

        return view
    }

    private fun setOnItemClickListener(){
        val intent_view_all_mark = Intent(this.context, ViewAllMark::class.java)

        adapter!!.setOnClickListener(object : CoordinatorAdapter.onItemClickListner {
            override fun onItemClick(position: Int) {
                Log.d("item click", tempArrayList[position].toString())
                intent_view_all_mark.putExtra("item_click", tempArrayList[position])
                startItemForResult.launch(intent_view_all_mark)
            }
        })
    }

    private fun searchView(){
        searchView!!.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                TODO("Not yet implemented")
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onQueryTextChange(newText: String?): Boolean {
                tempArrayList.clear()
                val searchText = newText!!.lowercase(Locale.getDefault())
                if(searchText.isNotEmpty()){
                    itemsArray.forEach{
                        if(it.name!!.lowercase(Locale.getDefault()).contains(searchText)){
                            tempArrayList.add(it)
                        }
                        if(it.batch!!.lowercase(Locale.getDefault()).contains(searchText)){
                            tempArrayList.add(it)
                        }
                    }
                    Log.d("list", tempArrayList.toString())
                    recyclerViewAdapter!!.adapter!!.notifyDataSetChanged()
                }else{
                    tempArrayList.clear()
                    tempArrayList.addAll(itemsArray)
                    recyclerViewAdapter!!.adapter!!.notifyDataSetChanged()
                }
                return false
            }

        })
    }

    // once the activity is finished than get the result
    private fun onItemActivityResult(requestCode: Int, result: ActivityResult){
        if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
            when (requestCode) {
                MY_ITEM_CODE_REQUEST -> {
                    val message = "Update Successfully"
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun getStudentMarkDetail(){
        var studentMarkData :StudentData? = null

        var name_array: ArrayList<String> = ArrayList()
        var mark_id_array : ArrayList<String> = ArrayList()
        var batch_id_array : ArrayList<String> = ArrayList()
        var batch_array : ArrayList<String> = ArrayList()
        var total_mark_array :ArrayList<String> = ArrayList()
        var status_array : ArrayList<String> = ArrayList()

        mDB.collection("Students")
            .get()
            .addOnSuccessListener { documents ->
                if(documents.size() > 0){
                    for (document in documents){
                        Log.d("_Student_detail_1", document.toString())
                        if (document != null){
                            val name = document.get("Name").toString()
                            val mark_id = document.get("Mark_ID").toString()
                            val batch_id = document.get("Batch_ID").toString()

                            name_array.add(name!!)
                            mark_id_array.add(mark_id!!)
                            batch_id_array.add(batch_id!!)

                            Log.d("afadsf1", name.toString())
                            Log.d("afadsf2", mark_id.toString())
                            Log.d("afadsf3", batch_id.toString())

                            Log.d("afadsf4", name_array.toString())
                            Log.d("afadsf5", mark_id_array.toString())
                            Log.d("afadsf6", batch_id_array.toString())
                        }
                    }
                }
            }.continueWith{
                for (batch_id_a in batch_id_array){
                    mDB.collection("Batch")
                        .document(batch_id_a!!)
                        .get()
                        .addOnSuccessListener { document ->
                            Log.d("_Student_detail_3", document.toString())
                            val batch = document.get("Intake_MntYear").toString()

                            batch_array.add(batch!!)
                        }
                }

            }.continueWith{
                for (mark_id_a in mark_id_array){
                    mDB.collection("Mark")
                        .document(mark_id_a!!)
                        .get()
                        .addOnSuccessListener { document ->

                            val total_mark = document.get("Total_Mark").toString()
                            val status = document.get("Status").toString()

                            total_mark_array.add(total_mark!!)
                            status_array.add(status!!)


                            itemsArray.clear()
                            val num = name_array.size
                            for ( i in name_array.indices){
                                if(mark_id_array.size == num && status_array.size == num && batch_array.size == num && total_mark_array.size == num) {
                                    itemsArray.add(
                                        StudentData(
                                            name = name_array[i],
                                            mark_id = mark_id_array[i],
                                            status = status_array[i],
                                            batch = batch_array[i],
                                            total_mark = total_mark_array[i].toInt()
                                        )
                                    )
                                }
                            }

                            Log.d("fadfs", itemsArray.toString())
                            tempArrayList.addAll(itemsArray)
                            recyclerViewAdapter!!.adapter!!.notifyDataSetChanged()
                        }
                }

            }
    }
}