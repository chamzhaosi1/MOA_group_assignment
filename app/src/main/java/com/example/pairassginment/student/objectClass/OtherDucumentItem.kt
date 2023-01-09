package com.example.pairassginment.student.objectClass

import android.os.Parcel
import android.os.Parcelable

data class OtherDucumentItem(
    val studentComment: String?,
    val dateSubmitted: String?,
    val dateApproved: String?,
    val dateReject: String?,
    val uploadedFile: String?,
    val submittedStatus: String?,
    val supervisorComment: String?,
    ) : Parcelable{
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
        parcel.writeString(studentComment)
        parcel.writeString(dateSubmitted)
        parcel.writeString(dateApproved)
        parcel.writeString(dateReject)
        parcel.writeString(uploadedFile)
        parcel.writeString(submittedStatus)
        parcel.writeString(supervisorComment)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<OtherDucumentItem> {
        override fun createFromParcel(parcel: Parcel): OtherDucumentItem {
            return OtherDucumentItem(parcel)
        }

        override fun newArray(size: Int): Array<OtherDucumentItem?> {
            return arrayOfNulls(size)
        }
    }

}

