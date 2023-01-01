package com.example.pairassginment.student

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.pairassginment.R
import com.example.pairassginment.databinding.ActivityDashboardBinding
import com.transferwise.sequencelayout.SequenceStep

class Dashboard : AppCompatActivity() {
//    internal var step1: SequenceStep? = null
//    internal var step2: SequenceStep? = null
//    internal var step3: SequenceStep? = null
//    internal var step4: SequenceStep? = null
//    internal var step5: SequenceStep? = null

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val step1 = binding.firstStep as SequenceStep
        val step3 = binding.thirdStep as SequenceStep

        //programatically activating
        step3.setActive(true)
        step3.setTitle("Active Step")
        step3.setAnchor("Date...")
        step3.setSubtitle("Subtitle of this step third.")

        //programatically seting style to Title
        step3.setTitleTextAppearance(R.style.TextAppearance_AppCompat_System)

    }
}