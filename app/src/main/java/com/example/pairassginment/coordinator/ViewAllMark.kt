package com.example.pairassginment.coordinator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pairassginment.R
import com.example.pairassginment.databinding.ActivityDashboardCoordinatorBinding
import com.example.pairassginment.databinding.ActivityViewAllMarkBinding

class ViewAllMark : AppCompatActivity() {
    private lateinit var binding: ActivityViewAllMarkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewAllMarkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUIReady()
    }

    private fun setUIReady(){
        val button = binding.floatingActionButton17
        val button1 = binding.floatingActionButton
        val button2 = binding.floatingActionButton2
        val button3 = view.findViewById<FloatingActionButton>(R.id.floatingActionButton3)
        val button4 = view.findViewById<FloatingActionButton>(R.id.floatingActionButton4)
        val button5 = view.findViewById<FloatingActionButton>(R.id.floatingActionButton5)
        val button6 = view.findViewById<FloatingActionButton>(R.id.floatingActionButton6)
        val button7 = view.findViewById<FloatingActionButton>(R.id.floatingActionButton7)
        val button8 = view.findViewById<FloatingActionButton>(R.id.floatingActionButton8)
        val button9 = view.findViewById<FloatingActionButton>(R.id.floatingActionButton9)
        val button10 = view.findViewById<FloatingActionButton>(R.id.floatingActionButton10)
        val button11 = view.findViewById<FloatingActionButton>(R.id.floatingActionButton11)
        val button12 = view.findViewById<FloatingActionButton>(R.id.floatingActionButton12)
        val button13 = view.findViewById<FloatingActionButton>(R.id.floatingActionButton13)
    }
}