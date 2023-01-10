package com.example.pairassginment.student

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pairassginment.R
import com.example.pairassginment.databinding.ActivityOtherSubmitFormBinding
import com.example.pairassginment.databinding.ActivityViewOtherSubmitFormBinding
import com.example.pairassginment.databinding.ActivityViewTopicsSubmitFormBinding
import com.example.pairassginment.student.objectClass.OtherDucumentItem
import com.example.pairassginment.student.objectClass.StudentDetail
import com.example.pairassginment.student.objectClass.ThreeTopicsItem

class ViewOtherSubmitForm : AppCompatActivity() {
    private lateinit var binding: ActivityViewOtherSubmitFormBinding;
    private var proposal_ppt_detail: OtherDucumentItem? = null;
    private var student_detail: StudentDetail? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewOtherSubmitFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        proposal_ppt_detail = intent.getParcelableExtra("item_clicked")
        student_detail = intent.getParcelableExtra("student_detail")

//        Log.d("TAG", "item: "+ item_topics)
        binding.studentNameIdTv.text = student_detail!!.student_name.toString() + " " + student_detail!!.student_id.toString()
        binding.studentCommentTv.text = proposal_ppt_detail!!.studentComment
        binding.uploadedFileNameTv.text = proposal_ppt_detail!!.uploadedFileOrg
        binding.supervisorCommentTv.text = proposal_ppt_detail!!.supervisorComment

        when(proposal_ppt_detail!!.submittedStatus){
            "Approved" ->
            {
                binding.studentNameIdTv.setBackgroundColor(this.getColor(R.color.approved_green))
                binding.studentCommentTv.setBackgroundColor(this.getColor(R.color.approved_green))
                binding.uploadedFileNameTv.setBackgroundColor(this.getColor(R.color.approved_green))
                binding.supervisorCommentTv.setBackgroundColor(this.getColor(R.color.approved_green))
            }

            else ->
            {
                binding.studentNameIdTv.setBackgroundColor(this.getColor(R.color.rejected_red))
                binding.studentCommentTv.setBackgroundColor(this.getColor(R.color.rejected_red))
                binding.uploadedFileNameTv.setBackgroundColor(this.getColor(R.color.rejected_red))
                binding.supervisorCommentTv.setBackgroundColor(this.getColor(R.color.rejected_red))
            }
        }
    }
}