package com.example.mvvm_cou_mov.repository

import com.example.mvvm_cou_mov.RetrofitService
import com.example.mvvm_cou_mov.dataMain.ListResultDTO
import com.example.mvvm_cou_mov.dataDetail.LoompaDetailDTO
import retrofit2.Response

class MainRepository constructor(private val retrofitService: RetrofitService?) {

    suspend fun getResponseResult(page: Int): Response<ListResultDTO>? {
        return retrofitService?.getCustomPost(page)
    }

    suspend fun getLoompaID(id: Int): Response<LoompaDetailDTO>? {
        return retrofitService?.getDetailLoompabyID(id)
    }

}