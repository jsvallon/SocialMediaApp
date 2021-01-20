package com.jsv.socialmediaapp.util

import com.google.gson.*
import com.jsv.socialmediaapp.model.Activity
import com.jsv.socialmediaapp.model.TypeData
import com.jsv.socialmediaapp.response.UserFeedResponse
import java.lang.reflect.Type

/*


data class UserFeedResponse(
    @SerializedName("status")
    val status: String?,

    @SerializedName("pagination")
    val pagination: PaginationInfo?,

    @SerializedName("data")
    val dataPost: List<Activity>?
)


data class Response(
    @SerializedName("orderId")
    val orderId: Int,

    @SerializedName("type")
    val type: Int,

    @SerializedName("note")
    val note: String? = null,

    @SerializedName("percent")
    val percent: List<PercentItem> = emptyList()
)



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
        val data: TypeData?
    )

 */
class DataDeserializer : JsonDeserializer<Activity> {

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Activity {
        checkNotNull(json)  // throws error here, instead of somewhere inside Gson.
        checkNotNull(typeOfT)
        checkNotNull(context)


        val jsonObject = json.asJsonObject
        val id = jsonObject.get("id").asInt as Int?
        val userId = jsonObject.get("userId").asInt as Int?
        val occurredAt = jsonObject.get("occurredAt").asString as String?
        val type = jsonObject.get("occurredAt").asString as String?

        return Activity(id, userId, type, occurredAt, when (type) {
                "NEW_COMMENT"-> {
                    jsonObject.extractList<TypeData.CommentResponse>("data", context)
                }
                "GITHUB_PUSH" -> jsonObject.extractList<TypeData.GitHubEventResponse>("data", context)
                "NEW_POST"-> jsonObject.extractList<TypeData.PostResponse>("data", context)
                else ->{}
            })
    }

    private inline fun <reified T> JsonObject.extractList(memberName: String, context: JsonDeserializationContext): T {
       val json = this.get(memberName)
       return context.deserialize<T>(json, T::class.java)
    }
}

private val JsonElement.asStringOrNull: String?
    get() = when {
        this.isJsonNull -> null
        else -> this.asString
    }

//class DataDeserializer : JsonDeserializer<UserFeedResponse> {
//
//    // @Throws(JsonParseException::class)
//    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext):     PercentInspectionResponse {
//        val jsonObject = json.asJsonObject
//        val gson = Gson()
//
//        val percent = UserFeedResponse()
//        percent.orderId = jsonObject.get("orderId").asInt
//        percent.type = jsonObject.get("type").asInt
//        val noteString = jsonObject.get("note")
//        if (!noteString.isJsonNull) {
//            percent.note = noteString.asString
//        }
//
//        val typePercent= TypePercentInspection()
//
//        val type = jsonObject.get("type").asInt
//
//        if (1 == type) {
//            val percentTypes = jsonObject.getAsJsonArray("percent")
//            for (percent in percentTypes) {
//                val percentClass= gson.fromJson(percent, Type1::class.java)
//                typePercent.type1.add(percentClass)
//            }
//        }
//
//        if (2 == type) {
//            val percentTypes = jsonObject.getAsJsonArray("percent")
//            for (percent in percentTypes) {
//                val percentClass= gson.fromJson(percent, ItemType2::class.java)
//                typePercent.type2.add(percentClass)
//            }
//        }
//
//        percent.percent = typePercent
//
//        return percent
//    }
//}