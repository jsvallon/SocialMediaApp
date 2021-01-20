package com.jsv.socialmediaapp.response

import com.google.gson.annotations.SerializedName
import com.jsv.socialmediaapp.model.Post


data class PostResponse(
    @SerializedName("type")
    val type: String?,

    @SerializedName("data")
    val data: Post?
)
//
//data class PostResponse(
//    @SerializedName("id")
//    val id: Int?,
//
//    @SerializedName("userId")
//    val userId: Int?,
//
//    @SerializedName("occurredAt")
//    val occurredAt: String?,
//
//    @SerializedName("type")
//    val type: String?,
//
//    @SerializedName("data")
//    val data: Post?
//) : IResponse