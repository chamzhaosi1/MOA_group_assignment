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
                    val message = "Approved Successfully"
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun getItems(){
        itemsArray = ArrayList();
        itemsArray.add(StudentData("Lee Wei Heng", 60,"Approved","May 2022", 5,  3,4,5,5,5,5,5,3,2,3,5,5, 5))
        itemsArray.add(StudentData("Cham Zhao si", 50,"Revised", "May 2022",3, 3,2,3,3,3,5,5,3,2,3,5,5, 5))
        itemsArray.add(StudentData("Chia Yue Shyang", 60,"Approved", "Sep 2022", 5, 3,4,5,5,5,5,5,3,2,3,5,5, 5))
        itemsArray.add(StudentData("Tan Zhen Xun", 40,"Approved", "Sep 2022", 1, 3,4,1,1,1,1,1,1,2,1,1,1, 1))
        itemsArray.add(StudentData("Wong Jing Yi", 5,"Pending", "May 2022", 5))
        itemsArray.add(StudentData("Wong Jing Yi", 0,"Havent", "May 2022"))
        itemsArray.add(StudentData("Lee Wei Heng", 60,"Approved", "May 2022", 5, 3,4,5,5,5,5,5,3,2,3,5,5, 5))
        itemsArray.add(StudentData("Cham Zhao si", 50,"Revised", "Oct 2022",3, 3,2,3,3,3,5,5,3,2,3,5,5, 5))
        itemsArray.add(StudentData("Chia Yue Shyang", 60,"Approved", "May 2022", 5, 3,4,5,5,5,5,5,3,2,3,5,5, 5))
        itemsArray.add(StudentData("Tan Zhen Xun", 40,"Approved", "May 2022", 1, 3,4,1,1,1,1,1,1,2,1,1,1, 1))
        itemsArray.add(StudentData("Wong Jing Yi", 5,"Pending", "Nov 2022", 5))
        itemsArray.add(StudentData("Wong Jing Yi", 0,"Havent", "May 2022"))
        itemsArray.add(StudentData("Lee Wei Heng", 60,"Approved", "Nov 2022", 5, 3,4,5,5,5,5,5,3,2,3,5,5, 5))
        itemsArray.add(StudentData("Cham Zhao si", 50,"Revised", "May 2022",3, 3,2,3,3,3,5,5,3,2,3,5,5, 5))
        itemsArray.add(StudentData("Chia Yue Shyang", 60,"Approved", "May 2022", 5, 3,4,5,5,5,5,5,3,2,3,5,5, 5))
        itemsArray.add(StudentData("Tan Zhen Xun", 40,"Approved", "May 2022", 1, 3,4,1,1,1,1,1,1,2,1,1,1, 1))
        itemsArray.add(StudentData("Wong Jing Yi", 5,"Pending", "May 2022", 5))
        itemsArray.add(StudentData("Wong Jing Yi", 0,"Havent", "May 2022"))

    }

}