package com.example.common.ext

import android.content.Context
import android.widget.Toast
import com.example.common.widget.GsonUtils

/**
 * 一些常用的方法
 */

fun Any?.toJsonStr(): String {
    return GsonUtils.toJson(this)
}

/**
 * toast
 */
fun Any.toast(context: Context) =
    Toast.makeText(context, this.toString(), Toast.LENGTH_SHORT).show()


