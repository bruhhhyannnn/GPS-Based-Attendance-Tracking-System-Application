package com.example.capstone.model

data class StudentModel(
    var studentNumber : String = "",
    var course : String = "",
    var YRSection : String = "",
    var circle: MutableList<String> = mutableListOf()
)
