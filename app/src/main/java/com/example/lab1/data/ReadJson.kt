package com.example.lab1.data

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader
import com.example.lab1.data.Stiprumai
import com.example.lab1.data.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.awaitResponse


fun ReadStiprumai(context: Context): List<Stiprumai> {

    val inputStream = context.assets.open("Stiprumai.json")
    val reader = BufferedReader(InputStreamReader(inputStream))

    val stiprumaiType = object : TypeToken<List<Stiprumai>>() {}.type
    val stiprumai: List<Stiprumai> = Gson().fromJson(reader, stiprumaiType)

    reader.close()
    return stiprumai
}
fun ReadUsers(context: Context): List<User> {

    val inputStream = context.assets.open("Vartotojai.json")
    val reader = BufferedReader(InputStreamReader(inputStream))

    val usersType = object : TypeToken<List<User>>() {}.type
    val users: List<User> = Gson().fromJson(reader, usersType)

    reader.close()
    return users
}
fun ReadUsers(context: Application): List<User> {

    val inputStream = context.assets.open("Vartotojai.json")
    val reader = BufferedReader(InputStreamReader(inputStream))

    val usersType = object : TypeToken<List<User>>() {}.type
    val users: List<User> = Gson().fromJson(reader, usersType)

    reader.close()
    return users
}

//fun ReadUsersFromApi(onResult: (List<User>?) -> Unit) {
//    val apiService = ApiClient.apiService
//
//    apiService.getUsers().enqueue(object : Callback<List<User>> {
//        override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
//            if (response.isSuccessful) {
//                val users = response.body()
//
//                onResult(users) // Pass the users to the callback
//            } else {
//                onResult(null) // Handle the API error response
//            }
//        }
//
//        override fun onFailure(call: Call<List<User>>, t: Throwable) {
//            t.printStackTrace()
//            onResult(null) // Handle network or other errors
//        }
//    })
//}
suspend fun ReadUsersFromApi(): List<User>? {
    return withContext(Dispatchers.IO) {
        try {
            val response = ApiClient.apiService.getUsers().awaitResponse()
            if (response.isSuccessful) {
                response.body() // Returns the List<User>
            } else {
                null // Handle the API error response
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null // Handle network or other exceptions
        }
    }
}