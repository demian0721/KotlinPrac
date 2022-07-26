package com.example.parkpromobile.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.parkpromobile.R
import com.example.parkpromobile.api.InterestTagResponse
import com.example.parkpromobile.ui.home.HomeFragment

class InterestTag (private val context: HomeFragment,
                   var dataset: ArrayList<InterestTagResponse>?
) : RecyclerView.Adapter<InterestTag.InterestTagViewHolder>() {

    //리사이클러뷰에서 사용할 데이터 미리 정의 -> 나중에 MainActivity 등에서 dataset 에 실제 데이터 추가

    inner class InterestTagViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val tagNameView: TextView?

        init {
            tagNameView = view.findViewById(R.id.tagName)
        }
    }

    //만들어진 뷰홀더 없을때 뷰홀더(레이아웃) 생성하는 함수
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InterestTagViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.interest_tag_recycler_item, parent, false)

        return InterestTagViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: InterestTagViewHolder, position: Int) {

        //recyclerview 가 viewHolder 를 가져와 데이터 연결할때 호출
        //적절한 데이터를 가져와서 그 데이터를 사용하여 뷰홀더의 레이아웃 채움
        val self = this
        viewHolder.tagNameView?.text = dataset!![position].name
    }


    override fun getItemCount(): Int = dataset?.size ?: 0
}