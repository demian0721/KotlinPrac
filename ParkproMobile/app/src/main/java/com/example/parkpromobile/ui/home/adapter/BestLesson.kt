package com.example.parkpromobile.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.parkpromobile.R
import com.example.parkpromobile.api.BestLessonResult
import com.example.parkpromobile.ui.home.HomeFragment

class BestLesson(
    private val context: HomeFragment,
    var dataset: ArrayList<BestLessonResult>?
) : RecyclerView.Adapter<BestLesson.BestLessonViewHolder>() {

    //리사이클러뷰에서 사용할 데이터 미리 정의 -> 나중에 MainActivity등에서 datalist에 실제 데이터 추가

    inner class BestLessonViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val proNameView: TextView
        val profileImgView: ImageView
        val likedCount: TextView
        val commentCount: TextView

        init {
            proNameView = view.findViewById(R.id.proName)
            profileImgView = view.findViewById(R.id.smallProfileImg)
            likedCount = view.findViewById(R.id.likedCount)
            commentCount = view.findViewById(R.id.commentCount)
        }
    }

    //만들어진 뷰홀더 없을때 뷰홀더(레이아웃) 생성하는 함수
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestLessonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.best_lesson_recycler_item, parent, false)

        return BestLessonViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: BestLessonViewHolder, position: Int) {

        //recyclerview가 viewholder를 가져와 데이터 연결할때 호출
        //적절한 데이터를 가져와서 그 데이터를 사용하여 뷰홀더의 레이아웃 채움
        val self = this
        viewHolder.proNameView.text = dataset!![position].proName
        Glide.with(self.context).load(dataset!![position].smallProfileImg)
            .into(viewHolder.profileImgView);
        viewHolder.profileImgView.setImageURI(dataset!![position].smallProfileImg?.toUri())
        viewHolder.likedCount.text = dataset!![position].coachingLikedCount.toString()
        viewHolder.commentCount.text = "(${dataset!![position].coachingCommentedCount})"
    }


    override fun getItemCount(): Int = dataset?.size ?: 0

}