package com.example.mvvm_cou_mov.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResultDTO(
    @SerializedName("age")
    val age: Int,
    @SerializedName("country")
    val country: String,
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
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("last_name")
    val last_name: String,
    @SerializedName("profession")
    val profession: String
):Serializable