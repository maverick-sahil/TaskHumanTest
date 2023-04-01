package com.example.taskhumanhometest.presentation.util

/**
 * Created by Sahil Salgotra on 01/04/23 5:00 PM
 */

import android.content.Context
import android.content.res.Resources
import android.text.format.DateUtils
import android.util.DisplayMetrics

fun Long.relativeDateTime(): String {
    val now = System.currentTimeMillis()
    return DateUtils.getRelativeTimeSpanString(
        this,
        now,
        DateUtils.MINUTE_IN_MILLIS
    ).toString()
}

val density: Float
    get() = Resources.getSystem().displayMetrics.density

fun Float.dp(): Float = this * density + 0.5f

fun Int.pxToDp(context: Context): Float {
    return this / (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}