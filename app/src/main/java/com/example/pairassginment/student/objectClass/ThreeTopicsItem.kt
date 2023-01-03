package com.example.pairassginment.student.objectClass

import android.os.Parcel
import android.os.Parcelable

data class ThreeTopicsItem(
    val abstractSubmitted: String?,
    val topicSubmitted: String?,
    val dateSubmitted: String?,
    val dateApproved: String?,
    val dateReject: String?,
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
        parcel.writeString(abstractSubmitted)
        parcel.writeString(topicSubmitted)
        parcel.writeString(dateSubmitted)
        parcel.writeString(dateApproved)
        parcel.writeString(dateReject)
        parcel.writeString(submittedStatus)
        parcel.writeString(supervisorComment)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ThreeTopicsItem> {
        override fun createFromParcel(parcel: Parcel): ThreeTopicsItem {
            return ThreeTopicsItem(parcel)
        }

        override fun newArray(size: Int): Array<ThreeTopicsItem?> {
            return arrayOfNulls(size)
        }
    }
}
