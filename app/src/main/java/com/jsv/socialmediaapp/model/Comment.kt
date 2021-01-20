package com.jsv.socialmediaapp.model

import com.google.gson.annotations.SerializedName
import com.jsv.socialmediaapp.response.IResponse

data class Comment(

    @SerializedName("id")
    val id: Int?,

    @SerializedName("occurredAt")
    val occurredAt: Int?,

    @SerializedName("type")
    val type: Int?,


    @SerializedName("message")
    val message : String?,

    @SerializedName("userId")
    val userId : Int?,

    @SerializedName("postId")
    val postId : Int?
): IResponse
