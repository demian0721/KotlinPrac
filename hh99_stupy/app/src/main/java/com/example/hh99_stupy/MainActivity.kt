package com.example.hh99_stupy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.hh99_stupy.apis.HttpRequestHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {
    private lateinit var job:Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
        setContentView(R.layout.activity_main)
        getRoomData()
    }
    override fun onDestroy() {
        super.onDestroy()
        job.cancel() // Activity종료시 job이 종료되도록 한다.
    }

    fun getRoomData() {
        launch(Dispatchers.Main) {
            // 앞에서 만들었던 코드를 호출하고 결과를 받아와서 화면에 출력해 준다.
            val result = HttpRequestHelper().getRoom()
            Log.d(HttpRequestHelper.TAG, result.toString())
        }
    }
}