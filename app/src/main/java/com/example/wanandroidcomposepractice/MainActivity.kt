package com.example.wanandroidcomposepractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import coil.annotation.ExperimentalCoilApi
import com.example.common.baseData.themeTypeState
import com.example.wanandroidcomposepractice.composable.MainCompose
import com.example.wanandroidcomposepractice.ui.theme.CustomThemeManager
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.pager.ExperimentalPagerApi

class MainActivity : ComponentActivity() {
    @ExperimentalCoilApi
    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val themeState = themeTypeState.value
            WindowCompat.setDecorFitsSystemWindows(window,false)

            CustomThemeManager.WanAndroidTheme(type = themeState) {
                ProvideWindowInsets {
                    MainCompose(onFinish = { finish() })
                }
            }
        }
    }
}