package com.jsv.socialmediaapp.model

import com.google.gson.annotations.SerializedName

sealed class TypeData {
    data class CommentResponse(
        @SerializedName("data")
        val data: Comment?
    )

    data class GitHubEventResponse(
        @SerializedName("data")
        val data: GitHubEvent?
    )

    data class PostResponse(
        @SerializedName("data")
        val data: Post?
    )
}