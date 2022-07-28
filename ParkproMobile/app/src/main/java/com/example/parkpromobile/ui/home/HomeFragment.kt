package com.example.parkpromobile.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parkpromobile.api.*
import com.example.parkpromobile.databinding.FragmentHomeBinding
import com.example.parkpromobile.ui.home.adapter.BestLesson
import com.example.parkpromobile.ui.home.adapter.CoachingsAnswered
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

    var mTags = ArrayList<InterestTagResponse>()
    var mVideos = ArrayList<CoachingsAnsweredResult>()
    var mBestLessons = ArrayList<BestLessonResult>()

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
        api.getInterestTags().enqueue(object : Callback<ArrayList<InterestTagResponse>> {

            override fun onResponse(
                call: Call<ArrayList<InterestTagResponse>>,
                response: Response<ArrayList<InterestTagResponse>>
            ) {
                val d = Log.d("INTEREST_TAGS", response.body().toString())
                if (response.body().toString().isNotEmpty())
                    response.body()?.run {
                        mTags = response.body()!!
                        refreshInterestTagRecycler()
                    }
            }

            override fun onFailure(call: Call<ArrayList<InterestTagResponse>>, t: Throwable) {
                Log.d("INTEREST_TAGS", t.message.toString())
                Log.d("INTEREST_TAGS", "fail")
            }


        })
        // 프로찾기 하단 인기 있는 프로 목록
        api.getBestLessons().enqueue(object : Callback<BestLessonResponse> {
            override fun onResponse(
                call: Call<BestLessonResponse>,
                response: Response<BestLessonResponse>
            ) {
                val d = Log.d("BEST_LESSONS", response.body().toString())
                if (response.body().toString().isNotEmpty())
                    response.body()!!.results?.run {
                        mBestLessons = response.body()?.results!!
                        refreshBestLessonRecyclerView()
                    }
            }

            override fun onFailure(call: Call<BestLessonResponse>, t: Throwable) {
                Log.d("BEST_LESSONS", t.message.toString())
                Log.d("BEST_LESSONS", "fail")
            }
        })

        return root

    }

    private val self = this
    private fun refreshBestLessonRecyclerView() {
        val adapter = BestLesson(this, mBestLessons)
        adapter.dataset = mBestLessons
        binding.bestLessonRecyclerView.adapter = adapter
        binding.bestLessonRecyclerView.layoutManager =
            LinearLayoutManager(self.context, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun refreshInterestTagRecycler() {
        val self = this
        val adapter = InterestTag(this, mTags)
        adapter.dataset = mTags
        adapter.setOnItemClickListener(object : InterestTag.OnItemClickListener {
            override fun onItemClick(v: View, data: InterestTagResponse, pos: Int) {
                api.getCoachingsAnswered(
                    tagId = data.id,
                    proId = null,
                    tagExists = null,
                    page = 1,
                    size = 4
                ).enqueue(object : Callback<CoachingsAnsweredResponse> {
                    override fun onResponse(
                        call: Call<CoachingsAnsweredResponse>,
                        response: Response<CoachingsAnsweredResponse>
                    ) {
                        val d = Log.d("COACHING_ANSWERED", response.body().toString())
                        if (response.body().toString().isNotEmpty())
                            response.body()?.results?.run {
                                mVideos = response.body()!!.results
                                refreshTagVideosRecycler()
                            }
                    }

                    override fun onFailure(call: Call<CoachingsAnsweredResponse>, t: Throwable) {
                        Log.d("bestLessons", t.message.toString())
                        Log.d("bestLessons", "fail")
                    }
                })
            }

        })
        binding.interestTagRecyclerView.adapter = adapter
        binding.interestTagRecyclerView.layoutManager =
            LinearLayoutManager(self.context, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun refreshTagVideosRecycler() {
        val adapter = CoachingsAnswered(this, mVideos)
        adapter.dataset = mVideos
        binding.tagVideoRecyclerView.adapter = adapter
        binding.tagVideoRecyclerView.layoutManager =
            LinearLayoutManager(self.context, LinearLayoutManager.HORIZONTAL, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}