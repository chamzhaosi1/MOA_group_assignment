package com.example.pairassginment.supervisor

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pairassginment.R
import com.example.pairassginment.supervisor.`object`.HomeItems
import com.example.pairassginment.supervisor.`object`.Student
import com.example.pairassginment.supervisor.recycleAdapter.StudentAdapter
import com.example.pairassginment.supervisor.recycleAdapter.SupervisorAdapter

class StudentList : Fragment(), StudentAdapter.OnItemClickListener {
    private lateinit var itemsArray: ArrayList<Student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_coordinate_student_list, container, false)
        getItems()

        view.findViewById<RecyclerView>(R.id.studentList_RV).layoutManager= LinearLayoutManager(context)
        view.findViewById<RecyclerView>(R.id.studentList_RV).adapter= StudentAdapter(itemsArray, this)

        return view
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(activity, itemsArray[position].studentName.toString(), Toast.LENGTH_SHORT).show()
        replaceFragment(StudentWork())
    }

    fun getItems() {
        itemsArray = ArrayList();
        itemsArray.add(Student("Cham Zhao Si", "ppt", "thesis", "txt" ))
        itemsArray.add(Student("Lee Wei Heng", "ppt", "thesis", "txt" ))

    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.frame_layout, fragment)
        fragmentTransaction?.commit()
    }
}