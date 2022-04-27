package com.yusufarisoy.lazygridapp.data.entity

import com.google.gson.annotations.SerializedName

data class BaseResponse<out T : Any>(

    @SerializedName("info")
    val info: Info,

    @SerializedName("results")
    val data: T
)
