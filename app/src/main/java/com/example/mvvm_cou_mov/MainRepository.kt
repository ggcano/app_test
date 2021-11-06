package com.example.mvvm_cou_mov

import com.example.mvvm_cou_mov.data.ListResultDTO
import com.example.mvvm_cou_mov.data.Loompa
import com.example.mvvm_cou_mov.data.ResultDTO
import retrofit2.Call
import retrofit2.Response

class MainRepository constructor(private val retrofitService: RetrofitService) {


    suspend fun getCustomPost(page: Int): Response<ListResultDTO> {
        return retrofitService.getCustomPost(page)
    }



}