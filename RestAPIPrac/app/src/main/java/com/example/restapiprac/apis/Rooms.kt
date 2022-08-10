package com.example.restapiprac.apis

data class ResultGetRooms (
    var roomId: String?,
    var title: String?,
    var usersNum: Int?,
    var maxPeople: Int?,
    var content: String?,
    var hashtags: ArrayList<String>?,
    var openKakao: String?,
    var rank: Int?,
    var isOn: Boolean?,
    var image: String?,
)