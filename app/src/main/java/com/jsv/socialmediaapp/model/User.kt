package com.jsv.socialmediaapp.model

import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("id")
    var id: String,

    @SerializedName("email")
    var email: String,

    @SerializedName("name")
    var name: String,

    @SerializedName("githubUsername")
    var githubUsername: String,

    @SerializedName("registeredAt")
    var registeredAt: String,

    @SerializedName("rating")
    var rating: String
)