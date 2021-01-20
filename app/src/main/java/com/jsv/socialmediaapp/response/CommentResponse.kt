package com.jsv.socialmediaapp.response

import com.google.gson.annotations.SerializedName
import com.jsv.socialmediaapp.model.Comment
import com.jsv.socialmediaapp.model.GitHubEvent

data class CommentResponse(
    @SerializedName("data")
    val data: Comment?
)


//data class CommentResponse(
//    @SerializedName("id")
//    val id: Int?,
//
//    @SerializedName("userId")
//    val userId: Int?,
//
//    @SerializedName("occurredAt")
//    val occurredAt: Int?,
//
//    @SerializedName("type")
//    val type: String?,
//
//    @SerializedName("data")
//    val data: Comment?
//) : IResponse