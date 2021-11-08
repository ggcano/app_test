package com.example.mvvm_cou_mov.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.viewbinding.library.activity.viewBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm_cou_mov.*
import com.example.mvvm_cou_mov.databinding.ActivityMainBinding
import com.example.mvvm_cou_mov.repository.MainRepository
import com.example.mvvm_cou_mov.viewmodel.MainViewModel
import com.example.mvvm_cou_mov.viewmodel.MyViewModelFactory

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
            if (binding.txtNumber.text.trim().isNotEmpty()) {
                val number: String = binding.txtNumber.text.toString()
                viewModel.getCustomPost(Integer.parseInt(number))
                viewModel.responseListMLD.observe(this, Observer { response ->
                    if (response.isSuccessful) {
                        adapterLoompa.setLoompaList(response.body())
                    }else{
                        println(getString(R.string.error_main_activity))
                    }
                })
            }
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

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }

}