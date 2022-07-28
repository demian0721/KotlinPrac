package com.example.parkpromobile.ui.home.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.parkpromobile.R
import com.example.parkpromobile.api.InterestTagResponse
import com.example.parkpromobile.ui.home.HomeFragment

class InterestTag(
    private val context: HomeFragment,
    var dataset: ArrayList<InterestTagResponse>?
) : RecyclerView.Adapter<InterestTag.InterestTagViewHolder>() {

    //리사이클러뷰에서 사용할 데이터 미리 정의 -> 나중에 MainActivity 등에서 dataset 에 실제 데이터 추가

    interface OnItemClickListener {
        fun onItemClick(v: View, data: InterestTagResponse, pos: Int)
    }

    private var listener: OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener) {
        Log.d(">>CLICK", "ON")
        this.listener = listener
    }

    inner class InterestTagViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var id: Int?
        private val tagNameView: TextView?

        init {
            id = 0
            tagNameView = view.findViewById(R.id.tagName)
        }

        fun bind(item: InterestTagResponse) {
            id = item.id
            tagNameView?.text = item.name

            val pos = adapterPosition
//            if (pos != RecyclerView.NO_POSITION) {
            itemView.setOnClickListener {
                Log.d(">>CLICK", "CLICK!! ID:${item.id}")
                listener?.onItemClick(itemView, item, pos)
            }
//            }


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
        viewHolder.bind(dataset!![position])
    }


    override fun getItemCount(): Int = dataset?.size ?: 0
}