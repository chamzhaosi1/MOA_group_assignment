package com.example.pairassginment.supervisor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pairassginment.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.skydoves.balloon.*
import com.skydoves.balloon.overlay.BalloonOverlayAnimation
import com.skydoves.balloon.overlay.BalloonOverlayOval
import com.skydoves.balloon.overlay.BalloonOverlayRect
import com.skydoves.balloon.overlay.BalloonOverlayRoundRect

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ViewAndEditMark : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_view_and_edit_mark, container, false)

        val button = view.findViewById<FloatingActionButton>(R.id.floatingActionButton17)
        val button1 = view.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        val button2 = view.findViewById<FloatingActionButton>(R.id.floatingActionButton2)
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
            button.showAlignBottom(balloon, 800)
        }

        val balloon1 = createBalloon(requireContext()) {
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


        val balloon2 = createBalloon(requireContext()) {
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

        val balloon3 = createBalloon(requireContext()) {
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

        val balloon4 = createBalloon(requireContext()) {
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

        val balloon5 = createBalloon(requireContext()) {
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

        val balloon6 = createBalloon(requireContext()) {
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

        val balloon7 = createBalloon(requireContext()) {
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

        val balloon8 = createBalloon(requireContext()) {
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

        val balloon9 = createBalloon(requireContext()) {
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

        val balloon10 = createBalloon(requireContext()) {
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
            button10.showAlignTop(balloon10)
        }

        val balloon11 = createBalloon(requireContext()) {
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
            button11.showAlignTop(balloon11)
        }

        val balloon12 = createBalloon(requireContext()) {
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

        val balloon13 = createBalloon(requireContext()) {
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

        // Inflate the layout for this fragment
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ViewAndEditMark.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ViewAndEditMark().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}