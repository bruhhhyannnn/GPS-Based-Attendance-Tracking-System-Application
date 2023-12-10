package com.example.capstone.model

data class FacultyModel(
    var classCode : String = "",
    var classDescription : String = "",
//    CAN BE CHANGED TO LOCATION AS DATA TYPE
    var classLocation : String = "",
//    CAN BE CHANGED TO TIME AS DATA TYPE
    var classHours : String = "",
    var circle: MutableList<String> = mutableListOf()
)
