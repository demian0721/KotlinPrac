package com.example.parkpromain.apis.CoachingsSalesBest

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

// 1. 인터페이스 작성
interface CoachingsSalesBest {
    @GET("/coachings/sales/best/")
    @Headers(
        "accept: application/json",
        "content-type: application/json",
        "authorization: Token d8cf08777eb40706f2d056b8d2119e3f1a5de6a9"
    )
    fun coachingsSalesBest(): Call<CoachingsSalesBestResponse>

}

// 2. 데이터 모델 정의
data class CoachingsSalesBestResponse(
    var count: Int? = null,
    var next: String? = null,
    var previous: String? = null,
    var results: ArrayList<CoachingsSalesBestResult>? = null,
)

// 2-1. Response Data 내부 클래스 (Response Data 내부 배열)
data class CoachingsSalesBestResult(
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

// 3. 레파짓토리 작성 (실제 API 통신하는 부분)
class CoachingsSalesBestRepository {

    companion object { // static 처럼 공유객체로 사용가능함. 모든 인스턴스가 공유하는 객체로서 동작함.
        private const val TAG = "GET_SALES_BEST"
        private const val BASE_URL = "http://parkpro-dev-api.gymtinc.com" // 주소
    }

    private lateinit var coachingsSalesBestService: CoachingsSalesBest
    private var coachingsSalesBestMutableLiveData:
            MutableLiveData<CoachingsSalesBestResponse> = MutableLiveData()

    init {
        val gson: Gson = GsonBuilder().setLenient().create()

        coachingsSalesBestService = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson)).build()
            .create(coachingsSalesBestService::class.java)
    }

    fun coachingsSalesBest() {
        coachingsSalesBestService.coachingsSalesBest()
            .enqueue(object : Callback<CoachingsSalesBestResponse> {
                override fun onResponse(
                    call: Call<CoachingsSalesBestResponse>,
                    response: Response<CoachingsSalesBestResponse>
                ) {
                    Log.d(
                        TAG,
                        "onResponse: ${
                            GsonBuilder().setPrettyPrinting().create().toJson(response.body())
                        }"
                    )
                    coachingsSalesBestMutableLiveData.postValue(response.body())
                }

                override fun onFailure(
                    call: Call<CoachingsSalesBestResponse>,
                    t: Throwable
                ) {
                    Log.d(TAG, "onFailure: error. cause: ${t.message}")
                    coachingsSalesBestMutableLiveData.postValue(null)
                }
            })
    }

    fun getCoachingsSalesBestResponseLiveData(): LiveData<CoachingsSalesBestResponse> {
        return this.coachingsSalesBestMutableLiveData
    }
}