package com.example.mvvm_cou_mov.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.viewbinding.library.activity.viewBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.mvvm_cou_mov.*
import com.example.mvvm_cou_mov.databinding.ActivityDetailBinding
import com.example.mvvm_cou_mov.repository.MainRepository
import com.example.mvvm_cou_mov.viewmodel.DetailViewModel
import com.example.mvvm_cou_mov.viewmodel.MyViewModelFactory

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
        getInfoLoompaDetail()
        setupText()
    }

    private fun setupText() {
        viewModel.responseDetailLoompaMLD.observe(this, Observer { response ->
            if (response.isSuccessful) {
                binding.txtEmail.text = response.body()?.email
                binding.txtAge.text = "${response.body()?.age.toString()} Años"
                binding.txtEmail.text = response.body()?.email
                binding.txtName.text =
                    "${response.body()?.first_name} ${response.body()?.last_name}"
                binding.txtDescription.text = response.body()?.description
                binding.txtFavoriteFood.text = response.body()?.favorite?.food
                Glide.with(this).load(response.body()?.image)
                    .dontAnimate()
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .apply(RequestOptions.circleCropTransform())
                    .into(binding.imageLoompa)
            } else {
                println(getString(R.string.error_detail))
            }
        })
    }

    private fun getInfoLoompaDetail() {
        val value = intent.extras?.getString("current_id")
        if (value != null) {
            viewModel.getLoompaDetail(value.toInt())
        }
    }

    private fun setupToolbar() {
        val getExtra = intent.extras?.getString("current_id")
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "ID: $getExtra"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                this@DetailActivity.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }



}
