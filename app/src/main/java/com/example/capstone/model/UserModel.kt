package com.example.capstone.model

data class UserModel(
    var id : String = "",
    var email : String = "",
    var title : String = "",
    var firstName : String = "",
    var lastName : String = "",
    var profilePic : String = "",
    var circle : MutableList<String> = mutableListOf()
)
