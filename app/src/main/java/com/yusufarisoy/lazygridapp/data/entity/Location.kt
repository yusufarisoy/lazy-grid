package com.yusufarisoy.lazygridapp.data.entity

import com.google.gson.annotations.SerializedName

data class Location(

    @SerializedName("name")
    val name: String,

    @SerializedName("url")
    val url: String
)
