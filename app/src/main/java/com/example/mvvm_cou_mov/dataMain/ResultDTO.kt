package com.example.mvvm_cou_mov.dataMain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResultDTO(
    @SerializedName("age")
    val age: Int?=null,
    @SerializedName("country")
    val country: String?=null,
    @SerializedName("email")
    val email: String?=null,
    @SerializedName("favorite")
    val favorite: Favorite?=null,
    @SerializedName("first_name")
    val first_name: String?=null,
    @SerializedName("gender")
    val gender: String?=null,
    @SerializedName("height")
    val height: Int?=null,
    @SerializedName("id")
    val id: Int?=null,
    @SerializedName("image")
    val image: String?=null,
    @SerializedName("last_name")
    val last_name: String?=null,
    @SerializedName("profession")
    val profession: String?=null
):Serializable