package com.example.mvvm_cou_mov

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.viewbinding.library.activity.viewBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm_cou_mov.databinding.ActivityMainBinding
import java.io.LineNumberReader


class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    private val madapter = MovieAdapter()
    private val binding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val retrofitService = RetrofitService.getInstance()
        val mainRepository = MainRepository(retrofitService)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = madapter
        }

        viewModel = ViewModelProvider(this, MyViewModelFactory(mainRepository))[MainViewModel::class.java]




      /*  viewModel.errorMessage.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })*/

      /*  viewModel.loading.observe(this, Observer {
            if (it) {
                binding.progressDialog.visibility = View.VISIBLE
            } else {
                binding.progressDialog.visibility = View.GONE
            }
        })*/

        binding.button.setOnClickListener {
            val number: String = binding.editTextPhone.text.toString()
            viewModel.getCustomPost(Integer.parseInt(number))
            viewModel.myCustomResponse.observe(this, Observer { response ->
                binding.textView.text = response.body().toString()
                madapter.setMovies(response.body())
                 /* response.body()?.forEach {
                        Log.d("Response",it.current.toString())
                        Log.d("Response",it.results[1].country.toString())
                        Log.d("Response",it.total.toString())
                        Log.d("Response","--------------")
                    }*/

            })
        }


    }
}