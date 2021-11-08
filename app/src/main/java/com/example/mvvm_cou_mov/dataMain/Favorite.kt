package com.example.mvvm_cou_mov.dataMain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Favorite(
    @SerializedName("color")
    val color: String?=null,
    @SerializedName("food")
    val food: String?=null,
    @SerializedName("random_string")
    val random_string: String?=null,
    @SerializedName("song")
    val song: String?=null
):Serializable