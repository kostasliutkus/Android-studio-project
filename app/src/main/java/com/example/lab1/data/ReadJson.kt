package com.example.lab1.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader
import com.example.lab1.data.Stiprumai
import com.example.lab1.data.User
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