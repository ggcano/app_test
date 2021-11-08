package com.example.mvvm_cou_mov.dataMain

import com.google.gson.annotations.SerializedName

data class ListResultDTO (
    @SerializedName("results")
    val listLoompa: MutableList<ResultDTO>
)