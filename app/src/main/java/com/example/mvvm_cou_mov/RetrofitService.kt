package com.example.mvvm_cou_mov

import com.example.mvvm_cou_mov.data.ListResultDTO
import com.example.mvvm_cou_mov.dataDetail.LoompaDetailDTO
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {

    @GET("oompa-loompas")
    suspend fun getCustomPost(
        @Query("page") page: Int): Response<ListResultDTO>

    @GET("oompa-loompas/{currentId}")
    suspend fun getDetailLoompabyID(
        @Path("currentId") number: Int
    ): Response<LoompaDetailDTO>


    companion object {
        var retrofitService: RetrofitService? = null
        fun getInstance() : RetrofitService? {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://2q2woep105.execute-api.eu-west-1.amazonaws.com/napptilus/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService
        }

    }
}