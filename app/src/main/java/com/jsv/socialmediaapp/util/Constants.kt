package com.jsv.socialmediaapp.util

object Constants {

    // Endpoints
    const val BASE_URL = "https:spacebook-code-challenge.herokuapp.com/api/"
    const val LOGIN_URL = "session"
    const val POSTS_URL = "posts"

    const val USERS_URL = "users"
    const val FEED__URL = "feed"

    //SharePreference
    const val USER_TOKEN = "user_token"
    const val USER_ID = "user_id"
    const val USER_EMAIL = "user_email"
    const val USER_NAME = "user_name"
    const val USER_GITHUB_USERNAME = "user_github_username"
    const val USER_REGISTERED_AT = "user_registered_at"
    const val USER_RATING = "user_rating"

    //Type in Activity
    const val NEW_COMMENT = "NEW_COMMENT"
    const val NEW_POST = "NEW_POST"
    const val GITHUB_NEW_REPO = "GITHUB_NEW_REPO"
    const val GITHUB_NEW_PR = "GITHUB_NEW_PR"
    const val GITHUB_MERGED_PR = "GITHUB_MERGED_PR"
    const val GITHUB_PUSH = "GITHUB_PUSH"
}