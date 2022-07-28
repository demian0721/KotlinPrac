package com.example.parkpromobile.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.parkpromobile.R
import com.example.parkpromobile.api.CoachingsAnsweredResult
import com.example.parkpromobile.ui.home.HomeFragment

class CoachingsAnswered(
    private val context: HomeFragment,
    var dataset: ArrayList<CoachingsAnsweredResult>?
) : RecyclerView.Adapter<CoachingsAnswered.CoachingsAnsweredViewHolder>() {

    //리사이클러뷰에서 사용할 데이터 미리 정의 -> 나중에 MainActivity등에서 datalist에 실제 데이터 추가

    inner class CoachingsAnsweredViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var id: Int? = 0
        val videoThumbnailView: ImageView
        val proNameView: TextView

        init {
            videoThumbnailView = view.findViewById(R.id.videoThumbnailView)
            proNameView = view.findViewById(R.id.videoProName)
        }
    }

    //만들어진 뷰홀더 없을때 뷰홀더(레이아웃) 생성하는 함수
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoachingsAnsweredViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.tag_video_recycler_item, parent, false)

        return CoachingsAnsweredViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: CoachingsAnsweredViewHolder, position: Int) {

        //recyclerview가 viewholder를 가져와 데이터 연결할때 호출
        //적절한 데이터를 가져와서 그 데이터를 사용하여 뷰홀더의 레이아웃 채움
        val self = this
        val url: String?
        val fileName: String? = dataset!![position].coachingVideoUrl?.substringBeforeLast(".")
        viewHolder.proNameView.text = dataset!![position].proName
        url = "${fileName}.png"
        Glide.with(self.context)
            .load(url)
            .centerCrop()
            .into(viewHolder.videoThumbnailView);
    }


    override fun getItemCount(): Int = dataset?.size ?: 0

}