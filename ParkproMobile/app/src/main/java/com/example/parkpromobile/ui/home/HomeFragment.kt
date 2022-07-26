package com.example.parkpromobile.ui.home

import android.content.Intent
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
import com.example.parkpromobile.api.InterestTagResponse
import com.example.parkpromobile.databinding.FragmentHomeBinding
import com.example.parkpromobile.ui.home.adapter.BestLesson
import com.example.parkpromobile.ui.home.adapter.InterestTag
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.

    private val api = APIS.create();

    private val binding get() = _binding!!

    var mDataset = ArrayList<BestLessonResults>()
    var mTags = ArrayList<InterestTagResponse>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

    // API 호출 로직
        // 유저가 설정한 관심 태그 목록
        api.get_interest_tags().enqueue(object : Callback<ArrayList<InterestTagResponse>> {

            override fun onResponse(
                call: Call<ArrayList<InterestTagResponse>>,
                response: Response<ArrayList<InterestTagResponse>>
            ) {
                val d = Log.d("tags", response.body().toString())
                if (response.body().toString().isNotEmpty())
                    response.body()?.run {
                        mTags = response.body()!!
                        refreshInterestTagRecycler()
                    }
            }

            override fun onFailure(call: Call<ArrayList<InterestTagResponse>>, t: Throwable) {
                Log.d("tags", t.message.toString())
                Log.d("tags", "fail")
            }


        })
        // 프로찾기 하단 인기 있는 프로 목록
        api.get_best_lessons().enqueue(object : Callback<BestLessonResponse> {
            override fun onResponse(
                call: Call<BestLessonResponse>,
                response: Response<BestLessonResponse>
            ) {
                val d = Log.d("bestLessons", response.body().toString())
                if (response.body().toString().isNotEmpty())
                    response.body()!!.results?.run {
                        mDataset = response.body()?.results!!
                        refreshBestLessonRecyclerView()
                    }
            }

            override fun onFailure(call: Call<BestLessonResponse>, t: Throwable) {
                Log.d("bestLessons", t.message.toString())
                Log.d("bestLessons", "fail")
            }
        })

        return root

    }

    private val self = this
    private fun refreshBestLessonRecyclerView() {
        val adapter = BestLesson(this, mDataset)
        adapter.dataset = mDataset
        binding.bestLessonRecyclerView.adapter = adapter
        binding.bestLessonRecyclerView.layoutManager =
            LinearLayoutManager(self.context, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun refreshInterestTagRecycler() {
        val adapter = InterestTag(this, mTags)
        adapter.dataset = mTags
        binding.interestTagRecyclerView.adapter = adapter
        binding.interestTagRecyclerView.layoutManager =
            LinearLayoutManager(self.context, LinearLayoutManager.HORIZONTAL, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}