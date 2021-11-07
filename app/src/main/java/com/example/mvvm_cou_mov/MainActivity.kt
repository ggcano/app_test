package com.example.mvvm_cou_mov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm_cou_mov.databinding.ActivityMainBinding

const val EXTRA_MESSAGE = "current_id"

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    private val adapterLoompa = LoompaAdapter { position -> onListItemClick(position) }
    private val binding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val retrofitService = RetrofitService.getInstance()
        val mainRepository = MainRepository(retrofitService)

        viewModel = ViewModelProvider(this, MyViewModelFactory(mainRepository))[MainViewModel::class.java]

        setupRecyclerView()
        loadRecyclerView()
    }

    private fun loadRecyclerView() {
        binding.button.setOnClickListener {
            val number: String = binding.editTextPhone.text.toString()
            viewModel.getCustomPost(Integer.parseInt(number))
            viewModel.responseListMLD.observe(this, Observer { response ->
                adapterLoompa.setMovies(response.body())
            })
        }
    }

    private fun onListItemClick(position: Int) {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE,position.toString())
        }
        startActivity(intent)
    }

    private fun setupRecyclerView(){
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = adapterLoompa
        }
    }
}