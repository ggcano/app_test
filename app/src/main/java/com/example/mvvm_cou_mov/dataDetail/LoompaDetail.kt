package com.example.mvvm_cou_mov.dataDetail

import com.google.gson.annotations.SerializedName

data class LoompaDetail(
    @SerializedName("age")
    val age: Int,
    @SerializedName("country")
    val country: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("favorite")
    val favorite: Favorite,
    @SerializedName("first_name")
    val first_name: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("last_name")
    val last_name: String,
    @SerializedName("profession")
    val profession: String,
    @SerializedName("quota")
    val quota: String
)