package com.example.pairassginment.supervisor.`object`

import android.os.Parcel
import android.os.Parcelable

data class StudentSubmission (
    var studName : String? = null,
    var studID : String? = null,
    var Final_Draft : ArrayList<otherDocument>? = null,
    var Final_PPT : ArrayList<otherDocument>? = null,
    var Final_Thesis : ArrayList<otherDocument>? = null,
    var Poster : ArrayList<otherDocument>? = null,
    var Proposal : ArrayList<otherDocument>? = null,
    var Proposal_PPT : ArrayList<otherDocument>? = null,
    var Topics : ArrayList<topic>? = null
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readArrayList(otherDocument::class.java.classLoader) as ArrayList<otherDocument>?,
        parcel.readArrayList(otherDocument::class.java.classLoader) as ArrayList<otherDocument>?,
        parcel.readArrayList(otherDocument::class.java.classLoader) as ArrayList<otherDocument>?,
        parcel.readArrayList(otherDocument::class.java.classLoader) as ArrayList<otherDocument>?,
        parcel.readArrayList(otherDocument::class.java.classLoader) as ArrayList<otherDocument>?,
        parcel.readArrayList(otherDocument::class.java.classLoader) as ArrayList<otherDocument>?,
        parcel.readArrayList(topic::class.java.classLoader) as ArrayList<topic>?
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(studName)
        parcel.writeString(studID)
        parcel.writeList(Final_Draft)
        parcel.writeList(Final_PPT)
        parcel.writeList(Final_Thesis)
        parcel.writeList(Poster)
        parcel.writeList(Proposal)
        parcel.writeList(Proposal_PPT)
        parcel.writeList(Topics)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StudentSubmission> {
        override fun createFromParcel(parcel: Parcel): StudentSubmission {
            return StudentSubmission(parcel)
        }

        override fun newArray(size: Int): Array<StudentSubmission?> {
            return arrayOfNulls(size)
        }
    }
}