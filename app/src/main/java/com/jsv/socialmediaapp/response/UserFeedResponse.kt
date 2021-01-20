package com.jsv.socialmediaapp.response

import com.google.gson.annotations.SerializedName
import com.jsv.socialmediaapp.model.Activity
import com.jsv.socialmediaapp.model.PaginationInfo


data class UserFeedResponse(
    @SerializedName("status")
    val status: String?,

    @SerializedName("pagination")
    val pagination: PaginationInfo?,

    @SerializedName("data")
    val dataPost: List<Activity>?
)


data class TypeData2 (
    var type1: CommentResponse,
    var type2: PostResponse,
    var type3: GitHubEventResponse
)
//
//    data class Activity(
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
//        val data: Any
//    )


//sealed class class TypeData {
//
//}
//    var type1: CommentResponse,
//    var type2: PostResponse,
//    var type3: GitHubEventResponse




//data class UserFeedResponse<T>(
//    @SerializedName("status")
//    val status: String?,
//
//    @SerializedName("pagination")
//    val pagination: PaginationInfo?,
//
//    @SerializedName("data")
//    val dataPost: List<T>?
//)



//
//data class UserFeedResponse<out T>(
//    @SerializedName("status")
//    val status: String?,
////    @SerializedName("data")
////    val dataComment: Comment?,
//
//    @SerializedName("pagination")
//    val pagination: PaginationInfo?,
//
//    @SerializedName("data")
//    val dataPost: List<out T>?
////
////    val dataPost: List<PostResponse>?,
//
////    @SerializedName("data")
////    val dataGitHubEvent: GitHubEventResponse?,
//
//
//
//)