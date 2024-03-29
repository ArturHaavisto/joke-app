package fi.tuni.tamk.tiko.haavisto.jokeapp

import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException
import kotlin.concurrent.thread

const val BASE_URL= "https://v2.jokeapi.dev/joke/Any"

/**
 * Fetches a joke or an error from JokeAPI and returns it.
 *
 * JokeAPI can send 3 kinds of json objects. It is either error, single part joke or two part joke.
 * If fetch does not work, general error is returned.
 */
fun fetchAndParse(flags: MutableList<String>? = null, search: String? = null, callBack: (String, Boolean) -> Unit) {
    thread {

        val url = BASE_URL + constructUrl(flags, search)
        val client = OkHttpClient()

        val request = Request.Builder()
            .url(url)
            .build()

        try {
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) throw IOException("Unexpected code $response")

                for ((name, value) in response.headers) {
                    println("$name: $value")
                }

                val obj = JSONObject(response.body!!.string())
                if(obj.getString("error") == "true") {
                    callBack(obj.getString("message"), true)
                }
                else if(obj.getString("type") == "single") {
                    callBack(obj.getString("joke"), false)
                } else {
                    val str = obj.getString("setup") + "\n\n" + obj.getString("delivery")
                    callBack(str, false)
                }
            }
        } catch (e: Exception) {
            callBack("Error, something went wrong!", true)
        }

    }
}

/**
 * Function builds the filter part of the url.
 */
fun constructUrl(flags: MutableList<String>? = null, search: String? = null): String {
    var filterString = ""
    if(flags != null || search != null) {
        filterString += "?"
    }
    if(flags != null) {
        filterString += "blacklistFlags="
        flags.forEachIndexed{i, item ->
            if(i > 0) {
                filterString += ","
            }
            filterString += item
        }
    }
    if(flags != null && search != null) {
        filterString += "&"
    }
    if(search != null) {
        filterString += "contains=$search"
    }
    return filterString
}