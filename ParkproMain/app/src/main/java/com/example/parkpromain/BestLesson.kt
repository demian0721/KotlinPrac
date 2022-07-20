package com.example.parkpromain

import com.google.gson.annotations.SerializedName

data class GetBestLessonResponse(
    var count: Int? = null,
    var next: String? = null,
    var previous: String? = null,
    var results: ArrayList<BestLessonResult>? = null,
)

data class BestLessonResult(
    @SerializedName("pro_id") var proId: Int?  = null,
    @SerializedName("pro_name") var proName: String?  = null,
    @SerializedName("small_profile_img") var smallProfileImg: String?  = null,
    @SerializedName("star_rate_avg") var starRateAvg: Double?  = null,
    @SerializedName("review_count") var reviewCount: Int?  = null,
    var point: Double?  = null,
    var score: Double?  = null,
    @SerializedName("coaching_liked_count") var coachingLikedCount: Int?  = null,
    @SerializedName("coaching_commented_count") var coachingCommentedCount: Int?  = null,
)