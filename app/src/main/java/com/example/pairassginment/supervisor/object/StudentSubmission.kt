package com.example.pairassginment.supervisor.`object`

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
)