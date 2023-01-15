package com.example.pairassginment.coordinator

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.pairassginment.R
import com.example.pairassginment.coordinator.objectClass.StudentData
import com.example.pairassginment.databinding.ActivityDashboardCoordinatorBinding
import com.example.pairassginment.databinding.ActivityViewAllMarkBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.skydoves.balloon.*
import com.skydoves.balloon.overlay.BalloonOverlayAnimation

class ViewAllMark : AppCompatActivity() {
    private lateinit var binding: ActivityViewAllMarkBinding
    private var studentData: StudentData? = null
    private var mDB: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewAllMarkBinding.inflate(layoutInflater)
        setContentView(binding.root)
        studentData = intent.getParcelableExtra<StudentData>("item_click")

        setUIReady()
        setBtnListener()
    }

    private fun setBtnListener(){
        val backBtn = binding.backBtn
        backBtn.setOnClickListener {
            val intentBack = Intent(this, StudentList::class.java)
            setResult(Activity.RESULT_CANCELED, intentBack)
            finish()
        }

        val approve_btn = binding.approveBtn
        approve_btn.setOnClickListener {
            // send data to data base
            updateDB()
        }
    }

    private fun updateDB(){
        var status: String? = null

        when(studentData!!.status){
            "Pending" -> status = "Approved"
            "Havent" -> status = "Approved"
            "Approved" -> status = "Revised"
            "Revised" -> status = "Approved"
        }

        mDB.collection("Mark")
            .document(studentData!!.mark_id!!)
            .update("Status", status)
            .addOnSuccessListener {
                val intentApprove = Intent(this, Dashboard::class.java)
                intentApprove.putExtra("from_view", "mark")
                intentApprove.putExtra("message", "Update Successfully")
                startActivity(intentApprove)
                finish()
            }
    }

    private fun setUIReady(){
        binding.textView3.text = studentData!!.name.toString()
        binding.textView4.text = studentData!!.total_mark.toString()

        when(studentData!!.status){
            "Approved" -> binding.approveBtn.setText("REVISE")
            "Havent" -> {
                binding.approveBtn.setText("WAITING")
                binding.approveBtn.isEnabled = false
                binding.approveBtn.setBackgroundColor(Color.parseColor("#c2c2c0"))
            }
            else -> binding.approveBtn.setText("APPROVE")
        }

        val button = binding.floatingActionButton17
        val button1 = binding.floatingActionButton
        val button2 = binding.floatingActionButton2
        val button3 =  binding.floatingActionButton3
        val button4 =  binding.floatingActionButton4
        val button5 =  binding.floatingActionButton5
        val button6 =  binding.floatingActionButton6
        val button7 =  binding.floatingActionButton7
        val button8 =  binding.floatingActionButton8
        val button9 =  binding.floatingActionButton9
        val button10 =  binding.floatingActionButton10
        val button11 =  binding.floatingActionButton11
        val button12 =  binding.floatingActionButton12
        val button13 =  binding.floatingActionButton13

        val ballon = createBalloon(this) {
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
            setText(
                "Laporan proposal (P01, C4(analisis)) : 5%  pengetahuan\n" +
                        "\n" +
                        "Perlu mempunyai elemen berikut:\n" +
                        "•\tPengenalan tentang projek dinyatakan dengan jelas  \n" +
                        "•\tPernyataan masalah dinyatakan dengan jelas\n" +
                        "•\tKajian literatur adalah relevan dengan pernyataan masalah\n" +
                        "•\tObjektif yang hendak dicapai selaras dengan pernyataan masalah\n" +
                        "•\tSkop projek bersesuaian dengan tahap prasiswazah \n" +
                        "•\tJangkaan hasil yang boleh dicapai\n" +
                        "•\tPerancangan projek dirangka dengan tersusun"
            )
            setTextColorResource(R.color.black)
            setBackgroundColorResource(R.color.tooltip_bg)
            setBalloonAnimation(BalloonAnimation.ELASTIC)
            setLifecycleOwner(lifecycleOwner)
        }
        button.setOnClickListener{
            button.showAlignBottom(ballon, 800)
        }

//        Log.d("fasdf,",studentData.toString())
        if(studentData!!.proposal != null){
            binding.markEt.setText(studentData!!.proposal!!.toString())
        }else{
            binding.markEt.setText("Not Yet")
        }


        val balloon1 = createBalloon(this) {
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
            setText("Abstrak (P01)\n" +
                    "0\n" +
                    "Abstrak tiada dalam tesis.\n" +
                    "1\n" +
                    "Abstrak merangkumi kurang daripada tiga elemen berikut iaitu pengenalan, pernyataan masalah, objektif, metodologi, hasil kajian dan kesimpulan DAN tidak ditulis menggunakan bahasa penulisan yang baik dalam Bahasa Melayu atau Bahasa Inggeris.\n" +
                    "2\n" +
                    "Abstrak merangkumi sekurang-kurangnya tiga elemen berikut iaitu pengenalan, pernyataan masalah, objektif, metodologi, hasil kajian dan kesimpulan DAN ditulis menggunakan bahasa penulisan yang baik dalam Bahasa Melayu dan Bahasa Inggeris.\n" +
                    "3\n" +
                    "Abstrak merangkumi enam elemen berikut iaitu pengenalan, pernyataan masalah, objektif, metodologi, hasil kajian dan kesimpulan yang mengambarkan kandungan keseluruhan projek DAN ditulis menggunakan bahasa penulisan yang baik dalam Bahasa Melayu dan Bahasa Inggeris.")
            setTextColorResource(R.color.black)
            setBackgroundColorResource(R.color.tooltip_bg)
            setBalloonAnimation(BalloonAnimation.ELASTIC)
            setLifecycleOwner(lifecycleOwner)
        }

        button1.setOnClickListener{
            button1.showAlignBottom(balloon1)
        }

        if(studentData!!.abstrak != null){
            binding.markEt1.setText(studentData!!.abstrak!!.toString())
        }else{
            binding.markEt1.setText("Not Yet")
        }


        val balloon2 = createBalloon(this) {
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
            setText("Pendahuluan (P01)\n" +
                    "0\n" +
                    "Pendahuluan tiada dalam tesis.\n" +
                    "1\n" +
                    "Pendahuluan merangkumi mana-mana satu elemen berikut iaitu pernyataan masalah, objektif, jangkaan hasil projek dan organisasi tesis tetapi tidak berkaitan dengan projek yang dijalankan DAN ditulis menggunakan gaya penulisan yang kurang tersusun.\n" +
                    "2\n" +
                    "Pendahuluan merangkumi mana-mana dua elemen berikut iaitu pernyataan masalah, objektif, jangkaan hasil projek dan organisasi tesis yang tidak mengambarkan projek DAN ditulis menggunakan gaya penulisan kurang tersusun.\n" +
                    "3\n" +
                    "Pendahuluan merangkumi mana-mana tiga elemen berikut iaitu pernyataan masalah, objektif, jangkaan hasil projek dan organisasi tesis yang mengambarkan projek DAN ditulis menggunakan gaya penulisan tersusun.\n" +
                    "4\n" +
                    "Pendahuluan merangkumi semua empat elemen berikut iaitu pernyataan masalah, objektif, jangkaan hasil projek dan organisasi tesis yang mengambarkan projek DAN ditulis menggunakan gaya penulisan tersusun yang menunjukkan kesinambungan antara setiap elemen.")
            setTextColorResource(R.color.black)
            setBackgroundColorResource(R.color.tooltip_bg)
            setBalloonAnimation(BalloonAnimation.ELASTIC)
            setLifecycleOwner(lifecycleOwner)
        }

        button2.setOnClickListener{
            button2.showAlignTop(balloon2)
        }

//        Log.d("student mark", studentData!!.toString())
//        Log.d("student mark", studentData!!.abstrak!!.toString())

        if(studentData!!.pendahuluan != null) {
            binding.markEt2.setText(studentData!!.pendahuluan.toString())
        }else{
            binding.markEt2.setText("Not Yet")
        }


        val balloon3 = createBalloon(this) {
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
            setText("Kajian Literatur (P07)(LL)\n" +
                    "0\n" +
                    "Kajian literatur tiada dalam tesis.\n" +
                    "1\n" +
                    "Kajian literatur yang tidak relevan dan tanpa rumusan.\n" +
                    "2\n" +
                    "Kajian literatur yang relevan daripada pelbagai sumber dan tanpa rumusan.\n" +
                    "3\n" +
                    "Kajian literatur yang relevan daripada pelbagai sumber dan beserta rumusan ringkas.\n" +
                    "4\n" +
                    "Kajian literatur yang relevan daripada pelbagai sumber beserta pandangan dan rumusan secara kritis.\n" +
                    "5\n" +
                    "Kajian literatur yang relevan dan terkini daripada pelbagai sumber, beserta pandangan dan rumusan secara kritis.")
            setTextColorResource(R.color.black)
            setBackgroundColorResource(R.color.tooltip_bg)
            setBalloonAnimation(BalloonAnimation.ELASTIC)
            setLifecycleOwner(lifecycleOwner)
        }

        button3.setOnClickListener{
            button3.showAlignTop(balloon3)
        }

        if(studentData!!.kajian_literature != null) {
            binding.markEt3.setText(studentData!!.kajian_literature.toString())
        }else{
            binding.markEt3.setText("Not Yet")
        }

        val balloon4 = createBalloon(this) {
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
            setText("Metodologi\n" +
                    "0\n" +
                    "Metodologi tiada dalam tesis.\n" +
                    "1\n" +
                    "Pemilihan metodologi yang tidak sesuai dan tidak dihuraikan dengan baik.\n" +
                    "2\n" +
                    "Pemilihan metodologi yang sesuai tetapi tidak dihuraikan dengan baik.\n" +
                    "3\n" +
                    "Pemilihan metodologi yang sesuai serta dihuraikan dengan baik.\n" +
                    "4\n" +
                    "Pemilihan metodologi yang sesuai serta dihuraikan dengan baik dan terperinci.\n" +
                    "5\n" +
                    "Pemilihan metodologi yang sesuai serta dihuraikan dengan jelas, terperinci dan tersusun yang menunjukkan kesinambungan antara setiap langkah.")
            setTextColorResource(R.color.black)
            setBackgroundColorResource(R.color.tooltip_bg)
            setBalloonAnimation(BalloonAnimation.ELASTIC)
            setLifecycleOwner(lifecycleOwner)
        }

        button4.setOnClickListener{
            button4.showAlignTop(balloon4)
        }

        if(studentData!!.metodologi != null) {
            binding.markEt4.setText(studentData!!.metodologi.toString())
        }else{
            binding.markEt4.setText("Not Yet")
        }

        val balloon5 = createBalloon(this) {
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
            setText("Reka bentuk Sistem\n" +
                    "0\n" +
                    "Reka bentuk sistem tiada dalam tesis.\n" +
                    "1\n" +
                    "Pemilihan reka bentuk sistem yang tidak sesuai.\n" +
                    "2\n" +
                    "Pemilihan reka bentuk sistem yang sesuai tetapi tidak dihuraikan dengan baik.\n" +
                    "3\n" +
                    "Pemilihan reka bentuk sistem yang sesuai serta dihuraikan dengan baik.\n" +
                    "4\n" +
                    "Pemilihan reka bentuk sistem yang sesuai serta dihuraikan dengan baik, betul dan terperinci.\n" +
                    "5\n" +
                    "Pemilihan reka bentuk sistem yang sesuai serta dihuraikan dengan jelas dan terperinci yang digambarkan menggunakan perisian yang bersesuaian seperti CASE tool.")
            setTextColorResource(R.color.black)
            setBackgroundColorResource(R.color.tooltip_bg)
            setBalloonAnimation(BalloonAnimation.ELASTIC)
            setLifecycleOwner(lifecycleOwner)
        }

        button5.setOnClickListener{
            button5.showAlignTop(balloon5)
        }

        if(studentData!!.reka_bentuk_sistem != null) {
            binding.markEt5.setText(studentData!!.reka_bentuk_sistem.toString())
        }else{
            binding.markEt5.setText("Not Yet")
        }

        val balloon6 = createBalloon(this) {
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
            setText("Perlaksanaan\n" +
                    "0\n" +
                    "Perlaksanaan tiada dalam tesis.\n" +
                    "1\n" +
                    "Perlaksanaan tidak memenuhi objektif projek atau tidak relevan.\n" +
                    "2\n" +
                    "Perlaksanaan memenuhi sebahagian objektif projek.\n" +
                    "3\n" +
                    "Perlaksanaan memenuhi sebahagian objektif projek dan dibincangkan dengan ringkas.\n" +
                    "4\n" +
                    "Perlaksanaan memenuhi keseluruhan objektif projek dan dibincangkan dengan baik.\n" +
                    "5\n" +
                    "Perlaksanaan memenuhi keseluruhan objektif projek dan dibincangkan dengan jelas dan terperinci.")
            setTextColorResource(R.color.black)
            setBackgroundColorResource(R.color.tooltip_bg)
            setBalloonAnimation(BalloonAnimation.ELASTIC)
            setLifecycleOwner(lifecycleOwner)
        }

        button6.setOnClickListener{
            button6.showAlignTop(balloon6)
        }

        if(studentData!!.perlaksanan != null) {
            binding.markEt6.setText(studentData!!.perlaksanan.toString())
        }else{
            binding.markEt6.setText("Not Yet")
        }

        val balloon7 = createBalloon(this) {
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
            setText("Perbincangan\n" +
                    "0\n" +
                    "Perbincangan tiada dalam tesis.\n" +
                    "1\n" +
                    "Perbincangan tidak memenuhi objektif projek atau tidak relevan.\n" +
                    "2\n" +
                    "Perbincangan memenuhi sebahagian objektif projek.\n" +
                    "3\n" +
                    "Perbincangan memenuhi sebahagian objektif projek dan ditulis dengan ringkas.\n" +
                    "4\n" +
                    "Perbincangan memenuhi keseluruhan objektif projek dan ditulis dengan baik.\n" +
                    "5\n" +
                    "Perbincangan memenuhi keseluruhan objektif projek dan ditulis dengan jelas dan terperinci.")
            setTextColorResource(R.color.black)
            setBackgroundColorResource(R.color.tooltip_bg)
            setBalloonAnimation(BalloonAnimation.ELASTIC)
            setLifecycleOwner(lifecycleOwner)
        }

        button7.setOnClickListener{
            button7.showAlignTop(balloon7)
        }

        if(studentData!!.perbincangan != null) {
            binding.markEt7.setText(studentData!!.perbincangan.toString())
        }else{
            binding.markEt7.setText("Not Yet")
        }

        val balloon8 = createBalloon(this) {
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
            setText("Kesimpulan dan Cadangan (P01)\n" +
                    "0\n" +
                    "Kesimpulan dan cadangan tiada dalam tesis.\n" +
                    "1\n" +
                    "Kesimpulan atau cadangan dinyatakan dengan ringkas.\n" +
                    "2\n" +
                    "Kesimpulan dibincangkan dengan baik dan cadangan dikemukakan dengan baik.\n" +
                    "3\n" +
                    "Kesimpulan dibincangkan dengan jelas dan menyeluruh dan cadangan kreatif dikemukakan.")
            setTextColorResource(R.color.black)
            setBackgroundColorResource(R.color.tooltip_bg)
            setBalloonAnimation(BalloonAnimation.ELASTIC)
            setLifecycleOwner(lifecycleOwner)
        }

        button8.setOnClickListener{
            button8.showAlignTop(balloon8)
        }

        if(studentData!!.kesimpulan_dan_cadangan != null) {
            binding.markEt8.setText(studentData!!.kesimpulan_dan_cadangan.toString())
        }else{
            binding.markEt8.setText("Not Yet")
        }

        val balloon9 = createBalloon(this) {
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
            setText("Rujukan (P01)\n" +
                    "0\n" +
                    "Tiada rujukan dalam tesis.\n" +
                    "1\n" +
                    "Terdapat rujukan yang tidak relevan dan tidak mengikut format penulisan rujukan.\n" +
                    "2\n" +
                    "Semua rujukan adalah relevan dan mengikut format penulisan rujukan.")
            setTextColorResource(R.color.black)
            setBackgroundColorResource(R.color.tooltip_bg)
            setBalloonAnimation(BalloonAnimation.ELASTIC)
            setLifecycleOwner(lifecycleOwner)
        }

        button9.setOnClickListener{
            button9.showAlignTop(balloon9)
        }

        if(studentData!!.rujukan != null) {
            binding.markEt9.setText(studentData!!.rujukan.toString())
        }else{
            binding.markEt9.setText("Not Yet")
        }

        val ballon10 = createBalloon(this) {
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
            setText("Sitasi Penulisan (P06)(EM)\n" +
                    "0\n" +
                    "Semua kandungan tesis yang merangkumi penggunaan teks, gambar rajah, jadual ataupun lain-lain elemen disalin daripada sumber sedia ada.\n" +
                    "1\n" +
                    "Sebahagian besar kandungan tesis yang merangkumi penggunaan teks, gambar rajah, jadual ataupun lain-lain elemen disalin daripada sumber sedia ada tanpa menggunakan kaedah sitasi (citation) yang betul.\n" +
                    "2\n" +
                    "Sebahagian besar kandungan tesis yang merangkumi penggunaan teks, gambar rajah, jadual ataupun lain-lain elemen disalin daripada sumber sedia ada dirujuk dengan menggunakan kaedah sitasi (citation) yang betul.\n" +
                    "3\n" +
                    "Keseluruhan kandungan tesis yang merangkumi penggunaan teks, gambar rajah, jadual ataupun lain-lain elemen disalin daripada sumber sedia ada dirujuk dengan menggunakan kaedah sitasi (citation) yang betul.")
            setTextColorResource(R.color.black)
            setBackgroundColorResource(R.color.tooltip_bg)
            setBalloonAnimation(BalloonAnimation.ELASTIC)
            setLifecycleOwner(lifecycleOwner)
        }

        button10.setOnClickListener{
            button10.showAlignTop(ballon10)
        }

        if(studentData!!.sitasi_penulisan != null) {
            binding.markEt10.setText(studentData!!.sitasi_penulisan.toString())
        }else{
            binding.markEt10.setText("Not Yet")
        }

        val ballon11 = createBalloon(this) {
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
            setText("1.\t\tRekabentuk Projek (CPS9-P4(mekanisme)) - 5%  psikomotor\t[__/5]")
            setTextColorResource(R.color.black)
            setBackgroundColorResource(R.color.tooltip_bg)
            setBalloonAnimation(BalloonAnimation.ELASTIC)
            setLifecycleOwner(lifecycleOwner)
        }

        button11.setOnClickListener{
            button11.showAlignTop(ballon11)
        }

        if(studentData!!.rekabentuk != null) {
            binding.markEt11.setText(studentData!!.rekabentuk.toString())
        }else{
            binding.markEt11.setText("Not Yet")
        }

        val balloon12 = createBalloon(this) {
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
            setText("Konfigurasi persekitaran projek (CPS9-P5(respons ketara kompleks)) – 5%  psikomotor. Pelajar berupaya menunjukkan  kemahiran berikut:\n" +
                    "•\tmelaksanakan sebahagian langkah-langkah pembangunan atau implementasi projek\n" +
                    "•\tmengkonfigurasi atau memanipulasi persekitaran pembangunan dan pelaksanaan projek  \n")
            setTextColorResource(R.color.black)
            setBackgroundColorResource(R.color.tooltip_bg)
            setBalloonAnimation(BalloonAnimation.ELASTIC)
            setLifecycleOwner(lifecycleOwner)
        }

        button12.setOnClickListener{
            button12.showAlignTop(balloon12)
        }

        if(studentData!!.konfigurasi_persekitaran != null) {
            binding.markEt12.setText(studentData!!.konfigurasi_persekitaran.toString())
        }else{
            binding.markEt12.setText("Not Yet")
        }

        val balloon13 = createBalloon(this) {
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
            setText("Pemilihan metodologi/teknik/perisian (CSP4-CTPS) CT1(kebolehan mengenalpasti dan menganalisis masalah dalam situasi kompleks dan kabur, serta membuat penilaian yang berjustifikasi) 5%   ")
            setTextColorResource(R.color.black)
            setBackgroundColorResource(R.color.tooltip_bg)
            setBalloonAnimation(BalloonAnimation.ELASTIC)
            setLifecycleOwner(lifecycleOwner)
        }

        button13.setOnClickListener{
            button13.showAlignTop(balloon13)
        }

        if(studentData!!.pemilihan_methodologi_teknik_perisan != null) {
            binding.markEt13.setText(studentData!!.pemilihan_methodologi_teknik_perisan.toString())
        }else{
            binding.markEt13.setText("Not Yet")
        }
    }
}