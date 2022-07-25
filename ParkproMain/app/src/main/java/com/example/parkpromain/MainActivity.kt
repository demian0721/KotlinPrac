package com.example.parkpromain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parkpromain.adapter.BestLessonAdapter
import com.example.parkpromain.apis.CoachingsSalesBest.CoachingsSalesBestResult
import com.example.parkpromain.apis.CoachingsSalesBest.CoachingsSalesBestViewModel
import com.example.parkpromain.apis.Login.LoginViewModel

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "ParkProMainActivity"
    }

//    private var _binding: FragmentKidscafeSearchBinding? = null
//    private val binding get() = _binding!!

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var coachingsSalesBestViewModel: CoachingsSalesBestViewModel

    private var tokenString: TextView? = null
    var mDatas: ArrayList<CoachingsSalesBestResult>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()


    }

}