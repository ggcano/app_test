package com.example.mvvm_cou_mov.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Loompa(
    @SerializedName("current")
    val current: Int,
    @SerializedName("results")
    val resultsDto: List<ResultDTO>,
    @SerializedName("total")
    val total: Int
):Serializable