package com.jsv.socialmediaapp.response

import com.google.gson.annotations.SerializedName

data class EntiTyResponse<T> (

    @SerializedName("id")
    val id: Int?,

    @SerializedName("userId")
    val userId: Int?,

    @SerializedName("occurredAt")
    val occurredAt: String?,

    @SerializedName("type")
    val type: String?,

    @SerializedName("data")
    val data : T? = null
)