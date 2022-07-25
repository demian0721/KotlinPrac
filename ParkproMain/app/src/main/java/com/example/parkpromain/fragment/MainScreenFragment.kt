package com.example.parkpromain.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parkpromain.fragment.MainScreenFragment
import com.example.parkpromain.R
import com.example.parkpromain.adapter.BestLessonAdapter
import com.example.parkpromain.apis.CoachingsSalesBest.CoachingsSalesBestResult
import com.example.parkpromain.apis.CoachingsSalesBest.CoachingsSalesBestViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [MainScreen.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainScreenFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var coachingsSalesBestViewModel: CoachingsSalesBestViewModel

    var mDatas: ArrayList<CoachingsSalesBestResult>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        coachingsSalesBestViewModel =
            ViewModelProvider(this).get(CoachingsSalesBestViewModel::class.java)
        coachingsSalesBestViewModel.getCoachingsSalesBestResponseLiveData()
            .observe(this, Observer { response ->
                if (response != null) {
                    Log.d(TAG, "${response.results}")
                    mDatas = response.results
                }
            })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        coachingsSalesBestViewModel.coachingsSalesBest()
    }

    companion object {
        private const val TAG = "MainFragment"
    }
}