package com.example.parkpromain

import com.google.gson.annotations.SerializedName

data class PostLoginPayload(
    @SerializedName("user_type")
    val userType: String?,
    val phone: String?,
    val password: String?
)

data class PostLoginResponse(
    val id: Int?,
    val token: String?,
    @SerializedName("is_first_login")
    val isFirstLogin: Boolean?,
    @SerializedName("login_type")
    val loginType: String?,
)