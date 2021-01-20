package com.jsv.socialmediaapp.util


import com.jsv.socialmediaapp.model.Activity
import com.jsv.socialmediaapp.response.UserFeedResponse
import java.text.SimpleDateFormat



fun conferTimeString(postedAt: String): String {

    val parser =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm")
    val postedAtConvert: String?
    //    get() = formatter.format(parser.parse(postedAt))
    return  formatter.format(parser.parse(postedAt))
}

fun formatData(userFeedResponse: List<UserFeedResponse>?, type: String): List<Activity>? {
  return userFeedResponse?.map { data-> return@map data.dataPost
    }?.get(0)?.filter { it->it.type == type }
}

