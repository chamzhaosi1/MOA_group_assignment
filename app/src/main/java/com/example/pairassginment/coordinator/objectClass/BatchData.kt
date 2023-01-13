package com.example.pairassginment.coordinator.objectClass

import android.os.Parcel
import android.os.Parcelable

data class BatchData (
    var intake_mnt_year: String? = null,

    var topics_begin: String? = null,
    var proposal_ppt_begin: String? = null,
    var proposal_begin: String? = null,
    var final_draft_begin: String? = null,
    var final_ppt_begin: String? = null,
    var final_thesis_begin: String? = null,
    var poster_begin: String? = null,

    var topics_deadline: String? = null,
    var proposal_ppt_dealine: String? = null,
    var proposal_deadline: String? = null,
    var final_draft_deadline: String? = null,
    var final_ppt_deadline: String? = null,
    var final_thesis_deadline: String? = null,
    var poster_deadline: String? = null,
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
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
        parcel.writeString(intake_mnt_year)
        parcel.writeString(topics_begin)
        parcel.writeString(proposal_ppt_begin)
        parcel.writeString(proposal_begin)
        parcel.writeString(final_draft_begin)
        parcel.writeString(final_ppt_begin)
        parcel.writeString(final_thesis_begin)
        parcel.writeString(poster_begin)
        parcel.writeString(topics_deadline)
        parcel.writeString(proposal_ppt_dealine)
        parcel.writeString(proposal_deadline)
        parcel.writeString(final_draft_deadline)
        parcel.writeString(final_ppt_deadline)
        parcel.writeString(final_thesis_deadline)
        parcel.writeString(poster_deadline)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BatchData> {
        override fun createFromParcel(parcel: Parcel): BatchData {
            return BatchData(parcel)
        }

        override fun newArray(size: Int): Array<BatchData?> {
            return arrayOfNulls(size)
        }
    }
}

