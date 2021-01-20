package com.jsv.socialmediaapp.model

import com.google.gson.annotations.SerializedName

data class PaginationInfo (
    @SerializedName("currentPage")
    val currentPage: Int?,

    @SerializedName("perPage")
    val perPage: Int?,

    @SerializedName("totalEntries")
    val totalEntries: Int?,

    @SerializedName("totalPages")
    val totalPages: Int?
)