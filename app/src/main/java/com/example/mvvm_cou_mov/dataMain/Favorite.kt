package com.example.mvvm_cou_mov.dataMain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Favorite(
    @SerializedName("color")
    val color: String,
    @SerializedName("food")
    val food: String,
    @SerializedName("random_string")
    val random_string: String,
    @SerializedName("song")
    val song: String
):Serializable