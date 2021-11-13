package com.example.beginnerjetpacknavigation


import com.athanas.screenroute.ScreenRoute
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

sealed class Screen(route: String): ScreenRoute(route) {
    object MainScreen : Screen("main_screen")
    object DetailScreen : Screen("detail_screen")
}
