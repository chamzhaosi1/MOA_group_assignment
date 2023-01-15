package com.example.pairassginment.supervisor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pairassginment.R
import com.example.pairassginment.supervisor.`object`.MarkClass
import com.example.pairassginment.supervisor.`object`.StudWorkClass
import com.example.pairassginment.supervisor.recycleAdapter.MarkAdapter
import com.example.pairassginment.supervisor.recycleAdapter.StudWorkAdapter


class MarkList : Fragment(), MarkAdapter.OnItemClickListener {

    private lateinit var itemsArray: ArrayList<MarkClass>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_mark_list, container, false)
        getItems()

        view.findViewById<RecyclerView>(R.id.markList_rv).layoutManager =
            LinearLayoutManager(context)
        view.findViewById<RecyclerView>(R.id.markList_rv).adapter =
            MarkAdapter(itemsArray, this)

        return view
    }

    override fun onItemClick(position: Int) {
        replaceFragment((ViewAndEditMark()))
    }

    fun getItems() {
        itemsArray = ArrayList();

        itemsArray.add(MarkClass("Chao Zhao Si", "5/60"))
        itemsArray.add(MarkClass("Lee Wei Heng", "5/60"))


    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.frame_layout, fragment)
        fragmentTransaction?.commit()
    }
}