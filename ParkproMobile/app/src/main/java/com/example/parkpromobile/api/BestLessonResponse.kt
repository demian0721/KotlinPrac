package com.example.parkpromobile.api

import com.google.gson.annotations.SerializedName

data class BestLessonResponse(
    var count: Int? = null,
    var next: String? = null,
    var previous: String? = null,
    var results: ArrayList<BestLessonResults>? = null,
)

data class BestLessonResults(
    @SerializedName("pro_id") var proId: Int? = null,
    @SerializedName("pro_name") var proName: String? = null,
    @SerializedName("small_profile_img") var smallProfileImg: String? = null,
    @SerializedName("star_rate_avg") var starRateAvg: Double? = null,
    @SerializedName("review_count") var reviewCount: Int? = null,
    var point: Double? = null,
    var score: Double? = null,
    @SerializedName("coaching_liked_count") var coachingLikedCount: Int? = null,
    @SerializedName("coaching_commented_count") var coachingCommentedCount: Int? = null,
)
