package com.example.mvvm_cou_mov

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvm_cou_mov.data.ListResultDTO
import com.example.mvvm_cou_mov.data.ResultDTO
import com.example.mvvm_cou_mov.databinding.AdapterMovieBinding

class LoompaAdapter (private val onItemClicked: (position: Int) -> Unit): RecyclerView.Adapter<MainViewHolder>() {

        var movieList = mutableListOf<ResultDTO>()

        fun setMovies(movies: ListResultDTO?) {
            this.movieList = movies!!.listLoompa.toMutableList()
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = com.example.mvvm_cou_mov.databinding.AdapterMovieBinding.inflate(inflater, parent, false)
            return MainViewHolder(binding)
        }

        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

            val movie = movieList[position]
            holder.binding.name.text = movie.first_name
            Glide.with(holder.itemView.context).load(movie.image).into(holder.binding.imageview)
            holder.itemView.setOnClickListener {
                val id = movie.id
                onItemClicked(id)
            }
        }

        override fun getItemCount(): Int {
            return movieList.size
        }
    }

    class MainViewHolder(val binding: AdapterMovieBinding) : RecyclerView.ViewHolder(binding.root) {

    }
