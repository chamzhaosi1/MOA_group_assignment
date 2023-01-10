package com.example.pairassginment.student

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import com.example.pairassginment.databinding.ActivityOtherSubmitFormBinding
import com.example.pairassginment.student.objectClass.OtherDucumentItem
import com.example.pairassginment.student.objectClass.StudentDetail
import com.example.pairassginment.student.objectClass.ThreeTopicsItem
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import java.text.SimpleDateFormat
import java.util.*

class OtherSubmitForm : AppCompatActivity() {
    private lateinit var binding: ActivityOtherSubmitFormBinding
    private var student_detail: StudentDetail? = null;
    private var mDB: FirebaseFirestore = FirebaseFirestore.getInstance()
    private var storageReference = FirebaseStorage.getInstance()
    private var fileUrl : Uri? = null
    private var selectFileBtnClick : Int = 0;
    private var fileNameOnly: String? = null;
    private var item_other_detail: OtherDucumentItem? = null;

    private var MY_CODE_REQUEST: Int = 190;
    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        onActivityResult(MY_CODE_REQUEST, result)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtherSubmitFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        student_detail = intent.getParcelableExtra("student_detail")
        item_other_detail = intent.getParcelableExtra("item_clicked")

        Log.d("Student detail list", student_detail.toString())

        getStudentNameIDReady()
        getSubmissionFormLabelReady()
        getTopicsItemReadyWhenPending()
        setBtnOnClickListener()
    }

    private fun getTopicsItemReadyWhenPending(){
        if (item_other_detail != null){
            binding.uploadedFileNameTv.setText(item_other_detail!!.uploadedFileOrg.toString())
            binding.studentCommentEt.setText(item_other_detail!!.studentComment.toString())
        }
    }

    private fun getSubmissionFormLabelReady(){
        binding.topicLabelTv.setText("UPLOAD PRESENTATION SLIDE")
    }

    private fun getStudentNameIDReady(){
        binding.studentNameIdTv.text = student_detail!!.student_name.toString() + " " + student_detail!!.student_id.toString()
    }

    private fun setBtnOnClickListener(){
        binding.uploadBtn.setOnClickListener {
            selectFile();
        }

        binding.submitBtn.setOnClickListener {
            Log.d("Image Name", fileUrl.toString())

            if(!isEmptyInput()){
                uploadFile();
            }else{
                Toast.makeText(this, "Must write down your comment and upload a file.", Toast.LENGTH_SHORT).show();
            }
        }
        binding.backBtn.setOnClickListener {
            val intent = Intent(this, ListOfOtherDocuments::class.java)
            intent.putExtra("message", "Nothing updated")
            setResult(Activity.RESULT_OK, intent);
            finish()
        }
    }

    private fun isEmptyInput(): Boolean{
        Log.d("TAG", "abstract: "+binding.studentCommentEt.text.toString().trim());
        if(binding.studentCommentEt.text.toString().trim().isEmpty()){
            return true
        }

        if(selectFileBtnClick!! <= 0){
            return true
        }

        return false
    }

    private fun uploadFile(){

        var fileRef : StorageReference? = storageReference.reference

        // it will get the file name only, if got fileUrl.lastPathSegment
        var documentRef = fileRef!!.child("uploadedFile/${fileUrl!!.lastPathSegment}")

        // It will upload the file based on the url that we uploaded
        documentRef.putFile(fileUrl!!)
            .addOnSuccessListener {
                val proposal_ppt_collection = mDB.collection("Submission")
                val student_comment = binding.studentCommentEt.text.toString()
                val uploaded_file_org_name = fileNameOnly
                val uploaded_file_firebase = fileUrl!!.lastPathSegment
                val date_submit = SimpleDateFormat("dd MMM yyyy").format(Date())

                val proposal_data = hashMapOf<String, Any>(
                    "Date_Submit" to date_submit,
                    "Student_Comment" to student_comment,
                    "File_Submitted_Org" to uploaded_file_org_name!!,
                    "File_Submited" to uploaded_file_firebase!!,
                    "Status" to "Pending"
                )

                proposal_ppt_collection
                    .document(student_detail!!.submission_id!!)
                    .collection("Proposal_PPT")
                    .add(proposal_data)
                    .addOnSuccessListener { document ->
                        val document_id = document.id

                        proposal_ppt_collection
                            .document(student_detail!!.submission_id!!)
                            .collection("Proposal_PPT")
                            .document(document_id)
                            .update("Proposal_PPT_ID", document_id)
                            .addOnSuccessListener {
                                val intent = Intent(this@OtherSubmitForm, ListOfOtherDocuments::class.java)
                                intent.putExtra("message", "Data updated")
                                intent.putExtra("student_detail", student_detail)
                                startActivity(intent, )
                                finish()
                            }
                    }
            }
    }

    private fun selectFile(){
        var intent = Intent();

        // if you want find image => "image/* , word or pdf => "application/*
        intent.setType("application/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startForResult.launch(intent)
    }

    private fun onActivityResult(requestCode: Int, result: ActivityResult) {
        if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
            when (requestCode) {
                MY_CODE_REQUEST -> {
                    fileUrl = intent!!.data
                    fileNameOnly = getFileNameFromUri(this, fileUrl!!)
                    binding.uploadedFileNameTv.text = fileNameOnly
                    selectFileBtnClick++
                }
            }
        }
    }

    @SuppressLint("Range")
    private fun getFileNameFromUri(context: Context, uri: Uri): String? {
        val fileName: String?
        val cursor = context.contentResolver.query(uri, null, null, null, null)
        cursor?.moveToFirst()
        fileName = cursor?.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
        cursor?.close()
        return fileName
    }
}
