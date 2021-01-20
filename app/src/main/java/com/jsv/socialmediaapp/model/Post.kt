package com.jsv.socialmediaapp.model

import com.google.gson.annotations.SerializedName
import com.jsv.socialmediaapp.response.IResponse
import com.jsv.socialmediaapp.util.conferTimeString
/*
            "id": 17126,
            "userId": 51,
            "occurredAt": "2021-01-08T00:29:09.367Z",
            "type": "NEW_POST",
            "data": {
                "id": 4906,
                "title": "Post on Big Tech",
                "body": "is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                "postedAt": "2021-01-08T00:29:09.367Z",
                "author": {
                    "id": 51,
                    "email": "jsvallon@gmail.com",
                    "name": "John Vallon",
                    "githubUsername": "jsvallon",
                    "registeredAt": "2020-12-29T23:06:10.757Z",
                    "rating": null
                }
            }
* */
data class Post(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("body")
    val body: String?,

    @SerializedName("postedAt")
    val postedAt: String?,

    @SerializedName(" author")
    val  author: User?

): IResponse {
  /*

  * */
    val postedAtConvert: String?
      get() = postedAt?.let { conferTimeString(it) }

}