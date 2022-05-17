package fi.tuni.tamk.tiko.haavisto.jokeapp

import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException
import kotlin.concurrent.thread

const val BASE_URL= "https://v2.jokeapi.dev/joke/Any"

fun fetchAndParse(callBack: (String) -> Unit) {
    thread {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url(BASE_URL)
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            for ((name, value) in response.headers) {
                println("$name: $value")
            }

            val obj = JSONObject(response.body!!.string())
            if(obj.getString("error") == "true") {
                callBack(obj.getString("message"))
            }
            if(obj.getString("type") == "single") {
                callBack(obj.getString("joke"))
            } else {
                val str = obj.getString("setup") + "\n\n" + obj.getString("delivery")
                callBack(str)
            }
        }

    }
}