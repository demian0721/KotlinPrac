package com.example.retrofitbinding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitbinding.apis.RoomResponse

class RoomAdapter() : RecyclerView.Adapter<RoomAdapter.RoomViewHolder>() {
    override fun onCreateViewHolder(parent:ViewGroup,viewType:Int):RecyclerView.ViewHolder{
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemRoomBinding>(layoutInflater,viewType,parent,false)
        return ViewHolder(binding)
    }
}