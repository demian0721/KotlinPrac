package com.example.parkpromobile.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parkpromobile.api.APIS
import com.example.parkpromobile.api.BestLessonResponse
import com.example.parkpromobile.api.BestLessonResults
import com.example.parkpromobile.databinding.FragmentHomeBinding
import com.example.parkpromobile.ui.home.adapter.BestLesson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.

    val api = APIS.create();

    private val binding get() = _binding!!
    private lateinit var adapter: BestLesson
    var mDataset = ArrayList<BestLessonResults>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        api.get_best_lessons().enqueue(object : Callback<BestLessonResponse> {
            override fun onResponse(
                call: Call<BestLessonResponse>,
                response: Response<BestLessonResponse>
            ) {
                val d = Log.d("log", response.body().toString())
                if (response.body().toString().isNotEmpty())
                    response.body()!!.results?.run {
                        mDataset = response.body()?.results!!
                        refreshRecyclerView()
                    }
            }

            override fun onFailure(call: Call<BestLessonResponse>, t: Throwable) {
                // 실패
                Log.d("log", t.message.toString())
                Log.d("log", "fail")
            }
        })

        return root

    }

    private val self = this
    private fun refreshRecyclerView() {
        val adapter = BestLesson(this, mDataset)
        adapter.dataset = mDataset
        binding.bestLessonRecyclerView.adapter = adapter
        binding.bestLessonRecyclerView.layoutManager =
            LinearLayoutManager(self.context, LinearLayoutManager.HORIZONTAL, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}