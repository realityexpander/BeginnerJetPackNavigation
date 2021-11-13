package com.example.beginnerjetpacknavigation


import com.athanas.screenroute.ScreenRoute
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

sealed class Screen(route: String): ScreenRoute(route) {
    object MainScreen : Screen("main_screen")
    object DetailScreen : Screen("detail_screen")
}

//sealed class Screen(val route: String) {
//    object MainScreen : Screen("main_screen")
//    object DetailScreen : Screen("detail_screen")
//
//    // /{arg1}/{arg2}/{arg3}
//    // using strings
//    fun withRequiredArgs(vararg value: String): String {
//        return buildString {
//            append(route)
//            value.forEach { v->
//                append("/$v")
//            }
//        }
//    }
//
//    // /{arg1}/{arg2}/{arg3}
//    fun withRequiredArgs(vararg value: NavArg.Required): String {
//        return buildString {
//            append(route)
//            value.forEach { (v)->
//                append("/$v")
//            }
//        }
//    }
//
//    // ?arg1={arg1}/arg2={arg2}/arg3={arg3}
//    fun withOptionalArgs(vararg args: NavArg.Optional): String {
//        var firstArg: Boolean = true
//
//        return buildString {
//            append(route)
//            args.forEach { (k,v) ->
//                append(if (firstArg) "?" else "/")
//                firstArg = false
//                append("$k=$v")
//            }
//        }
//    }
//
//    // ?arg1={arg1}/arg2/arg3={arg3}/arg4
//    fun withArgs(vararg args: NavArg): String {
//        var firstArg: Boolean = true
//
//        return buildString {
//            append(route)
//            args.forEach { arg ->
//                when(arg) {
//                    is NavArg.Required ->
//                        append("/${arg.arg}")
//                    is NavArg.Optional -> {
//                        append(if (firstArg) "?" else "/")
//                        firstArg = false
//                        append("${arg.key}=${arg.value}")
//                    }
//                }
//            }
//        }
//    }
//
//    fun withUrlArg(urlArg: String): String {
//        return buildString {
//            append(route)
//            append("/")
//            append(URLEncoder.encode(
//                urlArg, // http://alphaone.me/
//                StandardCharsets.UTF_8.toString()
//            ))
//        }
//    }
//
//}
//
//sealed class NavArg {
//    lateinit var arg: String
//    lateinit var key: String
//    lateinit var value: String
//
//    operator fun component1(): String {
//        if (::key.isInitialized) return key
//
//        return arg
//    }
//    operator fun component2(): String {
//        return value
//    }
//
//    class Required(arg: String): NavArg() {
//        init {
//            this.arg = arg
//        }
//    }
//
//    class Optional(key: String = "", value: String): NavArg() {
//        init {
//            this.key = key
//            this.value = value
//        }
//    }
//}
//
//
//// test NavArg class
//fun main() {
//    var out = ""
//
//    out = Screen.MainScreen.withRequiredArgs("abc", "123", "xyz")
//    println("withRequiredArgs=$out")
//
//    out = Screen.MainScreen.withRequiredArgs(
//        NavArg.Required("abc" ),
//        NavArg.Required("123" )
//    )
//    println("withRequiredArgs usingArgs=$out")
//
//    out = Screen.MainScreen.withOptionalArgs(
//        NavArg.Optional("abc", "hey"),
//        NavArg.Optional("123", "xyz")
//    )
//    println("withOptionalArgs=$out")
//
//    out = Screen.MainScreen.withArgs(
//        NavArg.Optional("userName", "abc"),
//        NavArg.Required("my Name"),
//        NavArg.Optional("screen", "xyz")
//    )
//    println("withMixedArgs=$out")
//
//    out = Screen.MainScreen.withUrlArg(
//        "http://alphaone.me/"
//    )
//    println("withUrlArg=$out")
//    println("withUrlArg decoded=${URLDecoder.decode(out, StandardCharsets.UTF_8.toString())}")
//
//}