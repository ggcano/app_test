package com.example.mvvm_cou_mov

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.viewbinding.library.activity.viewBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_cou_mov.databinding.ActivityDetailBinding
import com.example.mvvm_cou_mov.databinding.ActivityMainBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailViewModel
    private val binding: ActivityDetailBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setupToolbar()
        val retrofitService = RetrofitService.getInstance()
        val mainRepository = MainRepository(retrofitService)
        viewModel =
            ViewModelProvider(this, MyViewModelFactory(mainRepository))[DetailViewModel::class.java]
        getExtra()
        viewModel.responseDetailLoompa.observe(this, Observer { response ->
            if (response.isSuccessful) {
                binding.txtEmail.text = response.body()?.email
                binding.txtAge.text = response.body()?.age.toString()
                binding.txtName.text = response.body()?.first_name
            }
        })
    }

    private fun getExtra() {
        val extras = intent.extras
        val value = extras?.getString("current_id")
        if (value != null) {
            viewModel.getLoompaDetail(value.toInt())
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Detalle"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            android.R.id.home -> {
                this@DetailActivity.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
