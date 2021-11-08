package com.example.mvvm_cou_mov.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvm_cou_mov.dataMain.ListResultDTO
import com.example.mvvm_cou_mov.dataMain.ResultDTO
import com.example.mvvm_cou_mov.databinding.ItemLoompaBinding


class LoompaAdapter (private val onItemClicked: (position: Int) -> Unit): RecyclerView.Adapter<MainViewHolder>() {

        var oompaLoompaList = mutableListOf<ResultDTO>()

        fun setMovies(movies: ListResultDTO?) {
            this.oompaLoompaList = movies!!.listLoompa.toMutableList()
            notifyDataSetChanged()
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
                onItemClicked(id)
            }
        }

        override fun getItemCount(): Int {
            return oompaLoompaList.size
        }
    }

    class MainViewHolder(val binding: ItemLoompaBinding) : RecyclerView.ViewHolder(binding.root) {

    }
