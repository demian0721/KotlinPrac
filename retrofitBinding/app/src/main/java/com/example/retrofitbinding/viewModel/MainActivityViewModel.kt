package com.example.retrofitbinding.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.retrofitbinding.apis.API
import com.example.retrofitbinding.apis.RoomResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel {

    companion object{
        fun getRoomData(
//            onResponse: (a: List<RoomResponse>?) -> Int,
//            onFailure: () -> Int,
        ): LiveData<List<RoomResponse>> {
            val api = API.create()
            val data = MutableLiveData<List<RoomResponse>>()
            api.getRoom().enqueue(object : Callback<List<RoomResponse>> {
                override fun onResponse(
                    call: Call<List<RoomResponse>>,
                    response: Response<List<RoomResponse>>
                ) {
                    Log.d(API.TAG, response.isSuccessful.toString())
                    data.value=response.body()!!
                }

                override fun onFailure(call: Call<List<RoomResponse>>, t: Throwable) {
                    t.stackTrace
                }
            })
            return data
        }
    }

}