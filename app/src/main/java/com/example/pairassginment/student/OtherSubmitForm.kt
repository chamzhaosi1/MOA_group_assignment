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
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage

class OtherSubmitForm : AppCompatActivity() {
    private lateinit var binding: ActivityOtherSubmitFormBinding
    private var mDB: FirebaseFirestore = FirebaseFirestore.getInstance()
    private var storageReference = FirebaseStorage.getInstance()
    private var fileUrl : Uri? = null

    private var MY_CODE_REQUEST: Int = 100;

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        onActivityResult(MY_CODE_REQUEST, result)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtherSubmitFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.uploadBtn.setOnClickListener {
            selectImage();
        }

        binding.submitBtn.setOnClickListener {
            Log.d("Image Name", fileUrl.toString())
            uploadImage();
        }
    }

    private fun uploadImage(){
        var fileRef : StorageReference? = storageReference.reference

        // it will get the file name only, if got fileUrl.lastPathSegment
        var documentRef = fileRef!!.child("uploadedFile/${fileUrl!!.lastPathSegment}")

        // It will upload the file based on the url that we uploaded
        documentRef.putFile(fileUrl!!)
            .addOnSuccessListener {
                Log.d("Image URL", fileUrl .toString())
            }
    }

    private fun selectImage(){
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
                    binding.uploadedFileNameTv.text = getFileNameFromUri(this, fileUrl!!)
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
