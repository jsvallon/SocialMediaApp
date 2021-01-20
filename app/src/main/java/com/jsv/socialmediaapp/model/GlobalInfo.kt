package com.jsv.socialmediaapp.model

import com.google.gson.annotations.SerializedName


data class GlobalInfo(
    val post: Post?,
    val comment: Comment?,
    val gitHubEvent: GitHubEvent?
)


//data class GlobalInfo(
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
//    val type: Int?
//)