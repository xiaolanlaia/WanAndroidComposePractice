package com.example.wanandroidcomposepractice.initializer

import android.content.Context
import android.util.Log
import androidx.startup.Initializer
import com.example.net.base.NetUrl
import com.example.net.base.ServiceCreator

class NetWorkInitializer: Initializer<Boolean> {

    override fun create(context: Context): Boolean {
        Log.d("初始化", "初始化Retrofit2")
        ServiceCreator.initConfig(NetUrl.BASE_URL)
        return true
    }

    //是否需要在？之后初始化
    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }

}