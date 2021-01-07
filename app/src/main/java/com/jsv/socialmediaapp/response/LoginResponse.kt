package com.jsv.socialmediaapp.response

import com.google.gson.annotations.SerializedName
import com.jsv.socialmediaapp.model.User

data class LoginResponse(
    @SerializedName("status")
    var status: String?,

    @SerializedName("data")
    var data: User?
)