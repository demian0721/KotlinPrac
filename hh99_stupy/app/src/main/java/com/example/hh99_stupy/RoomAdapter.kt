package com.example.hh99_stupy

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hh99_stupy.apis.RoomResponse
import com.example.hh99_stupy.databinding.RoomRecyclerViewItemBinding

class RoomAdapter(
    private val context: List<RoomResponse>,
    var roomList: List<RoomResponse>?
) : RecyclerView.Adapter<RoomAdapter.RoomViewHolder>() {

    //리사이클러뷰에서 사용할 데이터 미리 정의 -> 나중에 MainActivity등에서 datalist에 실제 데이터 추가
    lateinit var binding: RoomRecyclerViewItemBinding

    inner class RoomViewHolder(
        private val binding: RoomRecyclerViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(roomResponse: RoomResponse) {
            binding.roomResponse = roomResponse
        }
    }

    //만들어진 뷰홀더 없을때 뷰홀더(레이아웃) 생성하는 함수
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        binding = RoomRecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RoomViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: RoomViewHolder, position: Int) {

        //recyclerview가 viewholder를 가져와 데이터 연결할때 호출
        //적절한 데이터를 가져와서 그 데이터를 사용하여 뷰홀더의 레이아웃 채움
        val self = this
        val rooms = roomList?.get(position)
        if (rooms != null) {
            viewHolder.bind(rooms)
        }
    }


    override fun getItemCount(): Int = roomList?.size ?: 0

}