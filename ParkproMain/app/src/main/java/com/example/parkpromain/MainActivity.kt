package com.example.parkpromain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val api = APIS.create()

    private var tokenString: TextView? = null
    private var bestLessons: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        //GET BEST LESSON API 요청
        bestLessons = findViewById(R.id.bestLessons)
        api.getBestLesson().enqueue(object : Callback<GetBestLessonResponse> {
            override fun onResponse(
                call: Call<GetBestLessonResponse>,
                response: Response<GetBestLessonResponse>
            ) {
                Log.d("GET_BEST_LESSON", response.toString())
                Log.d("GET_BEST_LESSON", response.body().toString())
                if(response.body().toString().isNotEmpty())
                    Log.i("EXIST_BEST_LESSON: ", "${response.body().toString().isNotEmpty()}")
                bestLessons?.text = response.body()?.results.toString()
            }

            override fun onFailure(call: Call<GetBestLessonResponse>, t: Throwable) {
                Log.d("err-1", t.message.toString())
                Log.d("err-2", "fail")
            }
        })

        //POST LOGIN API 요청
        val data = PostLoginPayload(userType = "U", phone = "01012312312", password = "zephyr09")
        tokenString = findViewById(R.id.tokenString)
        api.postLogin(data).enqueue(object : Callback<PostLoginResponse> {
            override fun onResponse(
                call: Call<PostLoginResponse>,
                response: Response<PostLoginResponse>
            ) {
                Log.d("log-1", response.toString())
                Log.d("log-2", response.body().toString())
                if (response.body().toString().isNotEmpty())
                    Log.i("IF", "${response.body().toString().isNotEmpty()}")
                tokenString?.text = response.body()?.token
            }

            override fun onFailure(call: Call<PostLoginResponse>, t: Throwable) {
                // 실패
                Log.d("err-1", t.message.toString())
                Log.d("err-2", "fail")
            }
        })
    }


}