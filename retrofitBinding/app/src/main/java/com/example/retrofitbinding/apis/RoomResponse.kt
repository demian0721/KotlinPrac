package com.example.retrofitbinding.apis

data class RoomResponse(
    val roomId: String?,
    val title: String?,
    val usersNum: Int?,
    val maxPeople: Int?,
    val content: String?,
    val hashtags: List<String>?,
    val openKakao: String?,
    val rank: Int?,
    val isOn: Boolean?,
    val image: String?,
)