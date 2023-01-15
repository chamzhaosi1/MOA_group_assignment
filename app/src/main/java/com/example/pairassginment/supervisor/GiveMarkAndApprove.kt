package com.example.pairassginment.supervisor

import android.Manifest
import android.app.DownloadManager
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.Environment.DIRECTORY_DOWNLOADS
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.pairassginment.R
import com.example.pairassginment.supervisor.`object`.otherDocument
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.skydoves.balloon.BalloonAnimation
import com.skydoves.balloon.BalloonSizeSpec
import com.skydoves.balloon.createBalloon
import com.skydoves.balloon.overlay.BalloonOverlayAnimation
import com.skydoves.balloon.showAlignTop
import java.util.Objects


class GiveMarkAndApprove : Fragment() {
    private var mDB: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val uid:String = FirebaseAuth.getInstance().currentUser!!.uid;
    private lateinit var storageReference: StorageReference
    private lateinit var ref: StorageReference
    var item_click: otherDocument? = null
    var fileSub:String? = null
    var submission_ID:String? = null
    var mark_ID:String? = null
    var stud_ID:String? = null
    var supComment:EditText? = null
    var mark:EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_give_mark_and_approve, container, false)
        val title = arguments?.getString("title")
        submission_ID = arguments?.getString("submission_ID")
        mark_ID = arguments?.getString("mark_ID")
        Log.d("msrk", mark_ID.toString() )
        stud_ID = arguments?.getString("stud_ID")
        mark = view.findViewById<EditText>(R.id.mark_et)
        getMark()

//        Log.d("hkjhkjhktitle", submission_ID.toString())

        item_click = arguments?.getParcelable<otherDocument>("item clicked")
        supComment = view.findViewById<EditText>(R.id.editTextTextPersonName)
        if (item_click?.supComment.toString() != "null") {
            supComment?.setText(item_click?.supComment.toString())
        }
        Log.d("hkjhkjhkitem", item_click.toString())
        view.findViewById<TextView>(R.id.textView2).text = title
        view.findViewById<TextView>(R.id.fileName_tv).text = item_click?.fileSubmissionOrg

        val mark = view.findViewById<EditText>(R.id.mark_et)
        val button = view.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        val approve = view.findViewById<Button>(R.id.button2)
        val resubmit = view.findViewById<Button>(R.id.button3)
        val save = view.findViewById<Button>(R.id.button4)
        val download = view.findViewById<Button>(R.id.button)

        download.setOnClickListener() {
            download()
        }

        val balloon = createBalloon(requireContext()) {
            setArrowSize(10)
            setWidth(BalloonSizeSpec.WRAP)
            setHeight(BalloonSizeSpec.WRAP)
            setArrowPosition(0.5f)
            setCornerRadius(4f)
            setArrowAlignAnchorPadding(10)
            setAlpha(0.9f)
            setPadding(10)
            setIsVisibleOverlay(true)
            setOverlayColorResource(R.color.balloon_overlay)
            setOverlayPadding(6f)
            setOverlayPaddingColorResource(R.color.colorPrimary)
            setBalloonOverlayAnimation(BalloonOverlayAnimation.FADE)
            setDismissWhenOverlayClicked(false)
            setText("Laporan proposal (P01, C4(analisis)) : 5%  pengetahuan\n" +
                    "\n" +
                    "Perlu mempunyai elemen berikut:\n" +
                    "•\tPengenalan tentang projek dinyatakan dengan jelas  \n" +
                    "•\tPernyataan masalah dinyatakan dengan jelas\n" +
                    "•\tKajian literatur adalah relevan dengan pernyataan masalah\n" +
                    "•\tObjektif yang hendak dicapai selaras dengan pernyataan masalah\n" +
                    "•\tSkop projek bersesuaian dengan tahap prasiswazah \n" +
                    "•\tJangkaan hasil yang boleh dicapai\n" +
                    "•\tPerancangan projek dirangka dengan tersusun")
            setTextColorResource(R.color.black)
            setBackgroundColorResource(R.color.tooltip_bg)
            setBalloonAnimation(BalloonAnimation.ELASTIC)
            setLifecycleOwner(lifecycleOwner)

        }

        button.setOnClickListener{
            mark.showAlignTop(balloon)
        }

        save.setOnClickListener {
            val update = mapOf(
                "Supervisor_Comment" to view.findViewById<EditText>(R.id.editTextTextPersonName).text.toString(),
            )

            mDB.collection("Submission").document(submission_ID!!) .collection(item_click!!.documentType!!) .document(item_click!!.document_ID!!)
                .update(update)
                .addOnSuccessListener {
                    Toast.makeText(activity, "record added successfully", Toast.LENGTH_SHORT).show()
                }

            val mark = mark.text.toString()

            val addMark = mapOf(
                "Proposal" to mark
            )

            Log.d("markshkjh", mark_ID.toString())
            if (mark_ID != "null") {
                mDB.collection("Mark").document(mark_ID!!).update(addMark).addOnSuccessListener {
                    replaceFragment(StudentList())
                }
            } else {
                mDB.collection("Mark").add(addMark).addOnSuccessListener {
                        document ->
                    val addMark_ID = mapOf(
                        "Mark_ID" to document.id
                    )
                    mDB.collection("Students").document(stud_ID!!).update(addMark_ID).addOnSuccessListener {
                        replaceFragment(StudentList())
                    }
                }
            }
        }

        approve.setOnClickListener {
            val status = "APPROVED"
            val update = mapOf(
                "Status" to status,
                "Supervisor_Comment" to view.findViewById<EditText>(R.id.editTextTextPersonName).text.toString(),
            )

            mDB.collection("Submission").document(submission_ID!!) .collection(item_click!!.documentType!!) .document(item_click!!.document_ID!!)
                .update(update)
                .addOnCompleteListener {
                    Toast.makeText(activity, "record added successfully", Toast.LENGTH_SHORT).show()
                }

            val mark = mark.text.toString()
            if (mark.isNotEmpty()) {

            }

            val addMark = mapOf(
                "Proposal" to mark
            )

            Log.d("markshkjh", mark_ID.toString())
            if (mark_ID != "null") {
                mDB.collection("Mark").document(mark_ID!!).update(addMark).addOnSuccessListener {
                    replaceFragment(StudentList())
                }
            } else {
                mDB.collection("Mark").add(addMark).addOnSuccessListener {
                        document ->
                    val addMark_ID = mapOf(
                        "Mark_ID" to document.id
                    )
                    mDB.collection("Students").document(stud_ID!!).update(addMark_ID).addOnSuccessListener {
                        replaceFragment(StudentList())
                    }
                }
            }
        }

        resubmit.setOnClickListener {
            val status = "REJECTED"
            val update = mapOf(
                "Status" to status,
                "Supervisor_Comment" to view.findViewById<EditText>(R.id.editTextTextPersonName).text.toString()
            )

            mDB.collection("Submission").document(submission_ID!!) .collection(item_click!!.documentType!!) .document(item_click!!.document_ID!!)
                .update(update)
                .addOnCompleteListener {
                    Toast.makeText(activity, "record added successfully", Toast.LENGTH_SHORT).show()
                    replaceFragment(StudentList())
                }
        }
        // Inflate the layout for this fragment
        return view
    }

    fun getMark() {
        if (mark_ID != "null") {
//            Log.d("darfe" , "asda")
            mDB.collection("Mark").document(mark_ID!!).get().addOnSuccessListener {
                document ->
                if (document != null) {
//                    Log.d("darfe" , "dsadsadsa")
                    mark?.setText(document.get("Proposal").toString())
                }
            }
        }
    }

    private fun download(){
        // Filename in Firebase
        fileSub = item_click?.fileSubmission

        Log.d("hkjhkjhk", fileSub.toString())
        storageReference = FirebaseStorage.getInstance().reference
        ref = storageReference.child("uploadedFile/$fileSub")
        ref.downloadUrl.addOnSuccessListener { uri ->
            val url = uri.toString()
            Toast.makeText(activity, "Success downloaded",
                Toast.LENGTH_LONG).show()

                downloadFiles()

        }.addOnFailureListener { e ->
            // handle failure
            Toast.makeText(activity, "Unable to get the file",
                Toast.LENGTH_LONG).show()
        }
    }

    private fun downloadFiles(){
        if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
        }

        ref.downloadUrl.addOnSuccessListener { uri ->
            val url = uri.toString()
            val request = DownloadManager.Request(Uri.parse(url))
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
            request.setTitle("Download")
            request.setDescription("Downloading File...")
            request.allowScanningByMediaScanner()
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileSub)
            val manager = activity?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            manager.enqueue(request)
        }.addOnFailureListener { e ->
            // handle failure
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.frame_layout, fragment)
        fragmentTransaction?.commit()
    }
}