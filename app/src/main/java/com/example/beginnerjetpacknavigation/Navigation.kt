package com.example.beginnerjetpacknavigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.athanas.screenroute.NavArg

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController)
        }
//        composable(route = Screen.DetailScreen.route + "/{userName}", // /{arg1}/{arg2}  // ?arg1={arg1}/arg2={arg2}
        composable(route = Screen.DetailScreen.route + "?userName={userName}/{lastName}/screen={screen}",
            arguments = listOf( // does not need to be in order of param string
                navArgument("lastName") {
                    type = NavType.StringType
                    defaultValue= "LastName Default"
                    nullable = true
                },
                navArgument("userName") {
                    type = NavType.StringType
                    defaultValue= "UserName Default"
                    nullable = false
                },
                navArgument("screen") {
                    type = NavType.StringType
                    defaultValue= "screen_default"
                    nullable = true
                }
            )
        ) { entry ->
            DetailScreen(userName = entry.arguments?.getString("userName"),
                lastName = entry.arguments?.getString("lastName"),
                screen = entry.arguments?.getString("screen")
            )
        }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    var text by remember {
        mutableStateOf("")
    }

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp, vertical = 100.dp)
    ) {
        TextField(value = text,
            onValueChange = {
                text = it
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier=Modifier.height(8.dp))
        Button(onClick = {
//                navController.navigate(Screen.DetailScreen.withRequiredArgs(text))
                navController.navigate(Screen.DetailScreen
                    .withArgs( // must be in order of param string
                        NavArg.Optional("userName", text),
                        NavArg.Required("$text!name"),
                        NavArg.Optional("screen", "$text*screen"),
                    ))
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "To Detail Screen")
        }
    }
}

@Composable
fun DetailScreen(userName: String?, lastName: String?, screen: String?) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp)
        ) {
            Text(text = "Hello, $userName", color=MaterialTheme.colors.onSurface)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "lastName=$lastName", color=MaterialTheme.colors.onSurface)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Screen=$screen", color=MaterialTheme.colors.onSurface)
        }
    }
}