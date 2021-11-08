package com.example.mvvm_cou_mov.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvm_cou_mov.dataMain.ListResultDTO
import com.example.mvvm_cou_mov.dataMain.ResultDTO
import com.example.mvvm_cou_mov.databinding.ItemLoompaBinding
import okhttp3.internal.notify


class LoompaAdapter (private val onItemClicked: (position: Int) -> Unit): RecyclerView.Adapter<MainViewHolder>() {

        var oompaLoompaList = mutableListOf<ResultDTO>()

        fun setLoompaList(list: ListResultDTO?) {
            if (list!= null){
                this.oompaLoompaList = list.listLoompa.toMutableList()
            notifyDataSetChanged()
            }


        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemLoompaBinding.inflate(inflater, parent, false)
            return MainViewHolder(binding)
        }

        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

            val loompa = oompaLoompaList[position]
            holder.binding.name.text = loompa.first_name
            holder.binding.txtId.text = loompa.id.toString()
            Glide.with(holder.itemView.context).load(loompa.image).into(holder.binding.imageview)
            holder.itemView.setOnClickListener {
                val id = loompa.id
                id?.let { view -> onItemClicked(view) }
            }
        }

        override fun getItemCount(): Int {
            return oompaLoompaList.size
        }
    }

    class MainViewHolder(val binding: ItemLoompaBinding) : RecyclerView.ViewHolder(binding.root) {

    }
