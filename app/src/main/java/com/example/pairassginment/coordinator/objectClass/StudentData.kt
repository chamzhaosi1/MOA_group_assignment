package com.example.pairassginment.coordinator.objectClass

import android.os.Parcel
import android.os.Parcelable

data class StudentData (
    val name : String? = null,
    val total_mark : Int? = null,
    val status: String? = null,
    val batch: String? = null,
    val proposal :Int? = null,
    val abstrak: Int? = null,
    val pendahuluan: Int? =null,
    val kajian_literature: Int? = null,
    val metodologi: Int? = null,
    val reka_bentuk_sistem: Int? = null,
    val perlaksanan: Int? = null,
    val perbincangan: Int? = null,
    val kesimpulan_dan_cadangan: Int? = null,
    val rujukan: Int? = null,
    val sitasi_penulisan: Int? = null,
    val rekabentuk: Int? = null,
    val konfigurasi_persekitaran: Int? = null,
    val pemilihan_methodologi_teknik_perisan: Int? = null

        ):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeValue(total_mark)
        parcel.writeString(status)
        parcel.writeString(batch)
        parcel.writeValue(proposal)
        parcel.writeValue(abstrak)
        parcel.writeValue(pendahuluan)
        parcel.writeValue(kajian_literature)
        parcel.writeValue(metodologi)
        parcel.writeValue(reka_bentuk_sistem)
        parcel.writeValue(perlaksanan)
        parcel.writeValue(perbincangan)
        parcel.writeValue(kesimpulan_dan_cadangan)
        parcel.writeValue(rujukan)
        parcel.writeValue(sitasi_penulisan)
        parcel.writeValue(rekabentuk)
        parcel.writeValue(konfigurasi_persekitaran)
        parcel.writeValue(pemilihan_methodologi_teknik_perisan)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StudentData> {
        override fun createFromParcel(parcel: Parcel): StudentData {
            return StudentData(parcel)
        }

        override fun newArray(size: Int): Array<StudentData?> {
            return arrayOfNulls(size)
        }
    }
}