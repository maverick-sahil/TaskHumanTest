package com.example.taskhumanhometest.data.util

import android.content.Context
import android.content.SharedPreferences
import com.example.taskhumanhometest.R

/**
 * Created by Sahil Salgotra on 01/04/23 12:39 PM
 */

/**
 * Class responsible for managing the authentication token for the user session.
 *
 * @param context The application context.
 */
class SessionManager(context: Context) {

    companion object {
        const val AUTH_TOKEN = "auth_token" // The key used to store the authentication token in shared preferences
        const val SAMPLE_TOKEN =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjQ3MTYsInVzZXJzIjp7InN0YXR1cyI6MCwidHlwZSI6MCwiaXNNb2JpbGVWZXJpZmllZCI6dHJ1ZX0sImlhdCI6MTY3OTU3MzU4N30.gaiGbeN9tWIojmvSj0imKtCWW0wMhLzN-UjmXevzuyk" // A sample token that can be used if the authentication token is not available
    }

    private var prefs: SharedPreferences = context.getSharedPreferences(
        context.getString(R.string.app_name),
        Context.MODE_PRIVATE
    )

    /**
     * Fetches the authentication token for the current user session.
     *
     * @return The authentication token as a string. Returns the sample token if the authentication token is not available.
     */
    fun fetchAuthToken(): String {
        return prefs.getString(AUTH_TOKEN, SAMPLE_TOKEN) ?: SAMPLE_TOKEN
    }
}
