package com.example.parkpromobile.api

import com.google.gson.annotations.SerializedName

data class CoachingsAnsweredResponse(
    var count: Int?,
    var next: String?,
    var previous: String?,
    var results: ArrayList<CoachingsAnsweredResult>
)

data class CoachingsAnsweredResult(
    var id: Int?,
    @SerializedName("coaching_video_url") var coachingVideoUrl: String?,
    var tags: ArrayList<String>?,
    @SerializedName("pro_name") var proName: String?,
)