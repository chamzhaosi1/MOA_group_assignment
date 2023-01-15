package com.example.pairassginment.supervisor.`object`

import android.os.Parcel
import android.os.Parcelable

data class topic (
    val title : String? = null,
    val abstract : String? = null,
    val dateSubmission : String? = null,
    val status : String? = null,
    val dateFeedback : String? =null,
    val documentType : String? = null,
    val document_ID : String? = null

):Parcelable {
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
        parcel.writeString(title)
        parcel.writeString(abstract)
        parcel.writeString(dateSubmission)
        parcel.writeString(status)
        parcel.writeString(dateFeedback)
        parcel.writeString(documentType)
        parcel.writeString(document_ID)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<topic> {
        override fun createFromParcel(parcel: Parcel): topic {
            return topic(parcel)
        }

        override fun newArray(size: Int): Array<topic?> {
            return arrayOfNulls(size)
        }
    }

}