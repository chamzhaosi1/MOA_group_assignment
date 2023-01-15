package com.example.pairassginment.supervisor.`object`

import android.os.Parcel
import android.os.Parcelable

data class otherDocument (

    val dateSubmission : String? = null,
    val fileSubmission : String? = null,
    val fileSubmissionOrg : String? = null,
    val status : String? = null,
    val studComment : String? = null,
    val documentType : String? = null,
    val dateFeedback : String? =null,

): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(dateSubmission)
        parcel.writeString(fileSubmission)
        parcel.writeString(fileSubmissionOrg)
        parcel.writeString(status)
        parcel.writeString(studComment)
        parcel.writeString(documentType)
        parcel.writeString(dateFeedback)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<otherDocument> {
        override fun createFromParcel(parcel: Parcel): otherDocument {
            return otherDocument(parcel)
        }

        override fun newArray(size: Int): Array<otherDocument?> {
            return arrayOfNulls(size)
        }
    }
}