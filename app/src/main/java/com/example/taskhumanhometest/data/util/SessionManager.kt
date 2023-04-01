package com.example.taskhumanhometest.data.util

import android.content.Context
import android.content.SharedPreferences
import com.example.taskhumanhometest.R

/**
 * Created by Sahil Salgotra on 01/04/23 12:39 PM
 */
class SessionManager(context: Context) {

    companion object {
        const val AUTH_TOKEN = "auth_token"
        const val SAMPLE_TOKEN =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjQ3MTYsInVzZXJzIjp7InN0YXR1cyI6MCwidHlwZSI6MCwiaXNNb2JpbGVWZXJpZmllZCI6dHJ1ZX0sImlhdCI6MTY3OTU3MzU4N30.gaiGbeN9tWIojmvSj0imKtCWW0wMhLzN-UjmXevzuyk"
    }

    private var prefs: SharedPreferences = context.getSharedPreferences(
        context.getString(R.string.app_name),
        Context.MODE_PRIVATE
    )

    fun fetchAuthToken(): String {
        return prefs.getString(AUTH_TOKEN, SAMPLE_TOKEN) ?: SAMPLE_TOKEN
    }
}
