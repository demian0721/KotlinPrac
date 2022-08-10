package com.example.restapiprac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.restapiprac.apis.ResultGetRooms
import com.example.restapiprac.apis.StupyAPI

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val res = { data: List<ResultGetRooms>? ->
            Log.d(">>>Stupy", data.toString())
        }
        val fail = { println("Fail TT") }
        StupyAPI.getRooms(res, fail)
    }
}