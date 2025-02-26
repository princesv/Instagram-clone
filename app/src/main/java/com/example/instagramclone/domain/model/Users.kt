package com.example.instagramclone.domain.model

data class Users(
    val name: String = "",
    val username: String = "",
    val userid: String = "",
    val imageUrl: String = "",
    val email:String="",
    val password:String="",
    val following: List<String> = emptyList(),
    val followers: List<String> = emptyList(),
    val totalPosts: String = "",
    val bio: String = "",
    val url: String = ""
)