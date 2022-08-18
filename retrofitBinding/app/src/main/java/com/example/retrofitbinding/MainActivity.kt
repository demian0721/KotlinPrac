package com.example.retrofitbinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.retrofitbinding.apis.API
import com.example.retrofitbinding.apis.RoomResponse
import com.example.retrofitbinding.viewModel.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val res = { data: List<RoomResponse>? ->
            Log.d(API.TAG, data.toString())
        }
        val fail = {
            Log.d(API.TAG, "is Failed... TT")
        }

        MainActivityViewModel.getRoomData(res,fail)
    }


}