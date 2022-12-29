package com.example.wanandroidcomposepractice.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.example.common.baseData.Nav
import com.example.wanandroidcomposepractice.nav.KeyNavigationRoute
import com.example.wanandroidcomposepractice.nav.NavigationHost
import com.example.wanandroidcomposepractice.public.AppBar
import com.example.wanandroidcomposepractice.public.BottomNavBar
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.statusBarsHeight
import com.google.accompanist.pager.ExperimentalPagerApi

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2022/12/29 9:10
 */
@ExperimentalCoilApi
@ExperimentalPagerApi
@Composable
fun MainCompose(
    navHostController: NavHostController = rememberNavController(),
    onFinish: () -> Unit
){

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: Nav.BottomNavScreen.HomeScreen.route

    if (isMainScreen(currentRoute)){
        Scaffold(
            contentColor = MaterialTheme.colors.background,
            topBar = {
                Column {
                    Spacer(
                        modifier = Modifier
                            .background(MaterialTheme.colors.primary)
                            .statusBarsHeight()
                            .fillMaxWidth()
                    )
                    MainTopBar(Nav.bottomNavRoute.value,navHostController)
                }
            },
            bottomBar = {
                Column {
                    BottomNavBar(
                        bottomNavScreen = Nav.bottomNavRoute.value,
                        navHostController = navHostController
                    )
                    Spacer(modifier = Modifier
                        .background(MaterialTheme.colors.primary)
                        .navigationBarsHeight()
                        .fillMaxWidth())
                }
            },

            content = { paddingValues: PaddingValues ->
                NavigationHost(navHostController, paddingValues, onFinish)
                OnTwoBackContent(navHostController)

            }
        )
    }else{
        NavigationHost(navHostController, onFinish = onFinish)
    }

}
/**
 * 在主界面点击两次返回按钮,返回到手机桌面,再重新打开app,此时显示退出时的主界面
 */
@Composable
private fun OnTwoBackContent(navHostController: NavHostController) {
    if (Nav.twoBackFinishActivity) {
        LaunchedEffect(Unit) {
            navHostController.navigate(Nav.bottomNavRoute.value.route) {
                popUpTo(navHostController.graph.findStartDestination().id) {
                    saveState = true
                }
                //避免重建
                launchSingleTop = true
                //重新选择以前选择的项目时，恢复状态
                restoreState = true
            }
            Nav.twoBackFinishActivity = false
        }
    }
}
@Composable
private fun MainTopBar(bottomNavScreen: Nav.BottomNavScreen, navHostController: NavHostController){
    when(bottomNavScreen){
        //首页
        Nav.BottomNavScreen.HomeScreen ->{
            AppBar(
                "首页",
                rightIcon = Icons.Default.Search,
                onRightClick = {navHostController.navigate(KeyNavigationRoute.SEARCH.route)}
            )
        }
    }
}

/**
 * 是否是首页内容
 */
fun isMainScreen(route: String): Boolean = when (route) {
    Nav.BottomNavScreen.HomeScreen.route,
    Nav.BottomNavScreen.ProjectScreen.route,
    Nav.BottomNavScreen.SquareScreen.route,
    Nav.BottomNavScreen.PublicNumScreen.route,
    Nav.BottomNavScreen.LearnScreen.route,
    Nav.BottomNavScreen.MineScreen.route -> true
    else -> false
}