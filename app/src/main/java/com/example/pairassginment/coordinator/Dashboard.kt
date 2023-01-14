package com.example.pairassginment.coordinator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.pairassginment.R
import com.example.pairassginment.databinding.ActivityDashboardCoordinatorBinding

class Dashboard : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardCoordinatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardCoordinatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val to_view = intent.getStringExtra("from_view")
        val message = intent.getStringExtra("message")

        if(message != null){
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }

        if(to_view != null){
            when(to_view){
                "batch" -> replaceFragment(BeginAndDeadline())
                else -> replaceFragment(StudentList())
            }
        }else{
            replaceFragment(StudentList())
        }


        binding.bottomNavigationView.setOnItemSelectedListener {

            when (it.itemId){

                R.id.Student -> replaceFragment(StudentList())
                R.id.Assignment -> replaceFragment(BeginAndDeadline())

                else -> {

                }
            }
            true
        }

    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}