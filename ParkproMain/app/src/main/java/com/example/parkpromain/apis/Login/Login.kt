package com.example.parkpromain.apis.Login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

// 1. 인터페이스 작성
interface Login {
    @POST("/users/login/")
    @Headers(
        "accept: application/json",
        "content-type: application/json"
    )
    fun login(
        @Body jsonparams: String?,
        phone: String?,
        password: String?
    ): Call<LoginResponse>
}

// 2. 데이터 모델 정의
data class LoginPayload(
    @SerializedName("user_type")
    val userType: String?,
    val phone: String?,
    val password: String?
)

data class LoginResponse(
    val id: Int?,
    val token: String?,
    @SerializedName("is_first_login")
    val isFirstLogin: Boolean?,
    @SerializedName("login_type")
    val loginType: String?,
)

// 3. 레파짓토리 작성 (실제 API 통신하는 부분)
class LoginRepository {

    companion object { // static 처럼 공유객체로 사용가능함. 모든 인스턴스가 공유하는 객체로서 동작함.
        private const val TAG = "PostLoginRepository"
        private const val BASE_URL = "http://parkpro-dev-api.gymtinc.com" // 주소
    }

    private lateinit var loginService: Login
    private var loginResponseMutableLiveData: MutableLiveData<LoginResponse> =
        MutableLiveData()

    init {
        val gson: Gson = GsonBuilder().setLenient().create()

        loginService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson)).build()
            .create(loginService::class.java)
    }

    fun login(userType: String?, phone: String?, password: String?) {
        loginService.login(userType, phone, password).enqueue(object : Callback<LoginResponse?> {
            override fun onResponse(
                call: Call<LoginResponse?>,
                response: Response<LoginResponse?>
            ) {
                Log.d(
                    TAG,
                    "onResponse: ${
                        GsonBuilder().setPrettyPrinting().create().toJson(response.body())
                    }"
                )
                loginResponseMutableLiveData.postValue(response.body())
            }

            override fun onFailure(call: Call<LoginResponse?>, t: Throwable) {
                // 실패
                Log.d(TAG, "onFailure: error. cause: ${t.message}")
                loginResponseMutableLiveData.postValue(null)
            }
        })
    }

    fun getLoginResponseLiveData(): LiveData<LoginResponse> {
        return this.loginResponseMutableLiveData
    }
}