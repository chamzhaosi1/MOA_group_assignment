package com.example.pairassginment.supervisor.`object`

data class StudentSubmission (
    val studName : String? = null,
    val studID : String? = null,
    val Final_Draft : ArrayList<otherDocument>? = null,
    val Final_PPT : ArrayList<otherDocument>? = null,
    val Final_Thesis : ArrayList<otherDocument>? = null,
    val Poster : ArrayList<otherDocument>? = null,
    val Proposal : ArrayList<otherDocument>? = null,
    val Proposal_PPT : ArrayList<otherDocument>? = null,
    val Topics : ArrayList<topic>? = null
)