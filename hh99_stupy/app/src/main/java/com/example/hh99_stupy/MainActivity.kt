package com.example.hh99_stupy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hh99_stupy.apis.HttpRequestHelper
import com.example.hh99_stupy.apis.RoomResponse
import com.example.hh99_stupy.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {
    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    private lateinit var binding: ActivityMainBinding
    var roomData: List<RoomResponse>? = null
    val self = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
        setContentView(R.layout.activity_main)
        getRoomData()
        if (roomData != null) {
            val recyclerViewRoom = binding.rvRooms
            val roomResponse = roomData
            val roomAdapter = RoomAdapter(roomData!!)

            recyclerViewRoom.adapter = roomAdapter
            recyclerViewRoom.layoutManager = LinearLayoutManager(this)
            recyclerViewRoom.setHasFixedSize(true)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel() // Activity종료시 job이 종료되도록 한다.
    }

    fun getRoomData() {
        launch(Dispatchers.Main) {
            // 앞에서 만들었던 코드를 호출하고 결과를 받아와서 화면에 출력해 준다.
            val result = HttpRequestHelper().getRoom()
            if (result != null) {
                roomData = result
            }
        }
    }
}

