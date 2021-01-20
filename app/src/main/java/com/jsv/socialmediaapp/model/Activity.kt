package com.jsv.socialmediaapp.model

import com.google.gson.annotations.SerializedName
import com.jsv.socialmediaapp.response.IResponse


data class Activity(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("userId")
    val userId: Int?,

    @SerializedName("occurredAt")
    val occurredAt: String?,

    @SerializedName("type")
    val type: String?,

    @SerializedName("data")
    val data: Any
) {

}


//sealed class Activity {
//    data class CommentResponse(
//        @SerializedName("id")
//        val id: Int?,
//
//        @SerializedName("userId")
//        val userId: Int?,
//
//        @SerializedName("occurredAt")
//        val occurredAt: Int?,
//
//        @SerializedName("type")
//        val type: String?,
//
//        @SerializedName("data")
//        val data: Comment?
//    ) : Activity()
//
//    data class GitHubEventResponse(
//        @SerializedName("id")
//        val id: Int?,
//
//        @SerializedName("userId")
//        val userId: Int?,
//
//        @SerializedName("occurredAt")
//        val occurredAt: Int?,
//
//        @SerializedName("type")
//        val type: String?,
//
//        @SerializedName("data")
//        val data: GitHubEvent?
//    ) : Activity()
//
//    data class PostResponse(
//        @SerializedName("id")
//        val id: Int?,
//
//        @SerializedName("userId")
//        val userId: Int?,
//
//        @SerializedName("occurredAt")
//        val occurredAt: String?,
//
//        @SerializedName("type")
//        val type: String?,
//
//        @SerializedName("data")
//        val data: Post?
//    ) : Activity()
//
//
//}
//



//data class Activity(
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
//    val type: Int?,
//
//    @SerializedName("data")
//    val dataComment: Comment?,
//
//    @SerializedName("data")
//    val dataPost: Post?,
//
//    @SerializedName("data")
//    val dataGitHubEvent: GitHubEvent?
//)