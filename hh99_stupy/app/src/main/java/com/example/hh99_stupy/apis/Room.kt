package com.example.hh99_stupy.apis

import kotlinx.serialization.Serializable

@Serializable
data class RoomResponse(
    var roomId: String?,
    var title: String?,
    var usersNum: Int?,
    var maxPeople: Int?,
    var content: String?,
    var hashtags: List<String>?,
    var openKakao: String?,
    var rank: Int?,
    var isOn: Boolean?,
    var image: String?,
)

