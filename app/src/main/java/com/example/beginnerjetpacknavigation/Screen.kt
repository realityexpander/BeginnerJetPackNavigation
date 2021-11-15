package com.example.beginnerjetpacknavigation


//import com.athanas.ScreenRouter2
import com.athanas.screenroute2.ScreenRoute2  // android library
import com.athanas.screenroute.ScreenRoute // java library

import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

sealed class Screen(route: String): ScreenRoute(route) {
    object MainScreen : Screen("main_screen")
    object DetailScreen : Screen("detail_screen")
}


// Test class
sealed class ___ScreenTest(route:String): ScreenRoute(route) {
    object MainScreen : ___ScreenTest("main_screen")
    object DetailScreen : ___ScreenTest("detail_screen")
}

sealed class ___ScreenTest2(route:String): ScreenRoute2(route) {
    object MainScreen : ___ScreenTest("main_screen")
    object DetailScreen : ___ScreenTest("detail_screen")
}

// Test ScreenRoute class (make sure VM has -ea turned on to see assertions)
fun main(args: Array<String> =  arrayOf()) {
    var out = ""

    out = ___ScreenTest.MainScreen.withRequiredArgs("abc", "123", "xyz")
    println("withRequiredArgs=$out")
    assert(out == "main_screen/abc/123/xyz")

    out = ___ScreenTest.MainScreen.withRequiredArgs(
        ScreenRoute.NavArg.Required("abc" ),
        ScreenRoute.NavArg.Required("123" )
    )
    println("withRequiredArgs usingArgs=$out")
    assert(out == "main_screen/abc/123")

    out = ___ScreenTest.DetailScreen.withOptionalArgs(
        ScreenRoute.NavArg.Optional("abc", "hey"),
        ScreenRoute.NavArg.Optional("123", "xyz")
    )
    println("withOptionalArgs=$out")
    assert(out == "detail_screen?abc=hey/123=xyz")

    out = ___ScreenTest.MainScreen.withArgs(
        ScreenRoute.NavArg.Optional("userName", "abc"),
        ScreenRoute.NavArg.Required("my Name"),
        ScreenRoute.NavArg.Optional("screen", "xyz")
    )
    println("withMixedArgs=$out")
    assert(out == "main_screen?userName=abc/my Name/screen=xyz" )

    out = ___ScreenTest.DetailScreen.withUrlArg(
        "http://alphaone.me/"
    )
    println("withUrlArg=$out")
    println("withUrlArg decoded=${URLDecoder.decode(out, StandardCharsets.UTF_8.toString())}")
    assert(out == "detail_screen/http%3A%2F%2Falphaone.me%2F")
    assert(URLDecoder.decode(out, StandardCharsets.UTF_8.toString()) == "detail_screen/http://alphaone.me/")

}
