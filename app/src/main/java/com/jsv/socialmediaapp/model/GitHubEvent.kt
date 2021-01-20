package com.jsv.socialmediaapp.model

import com.google.gson.annotations.SerializedName
import com.jsv.socialmediaapp.response.IResponse


class GitHubEvent (

    @SerializedName("id")
    val id: Int?,

    @SerializedName("userId")
    val userId: Int?,

    @SerializedName("occurredAt")
    val occurredAt: Int?,

    @SerializedName("type")
    val type: Int?,

    @SerializedName("githubId")
    val githubId: String?,

    @SerializedName("url")
    val url: String?,

    @SerializedName("branch")
    val branch: String?,

    @SerializedName("pullRequestNumber")
    val pullRequestNumber: String?
) : IResponse