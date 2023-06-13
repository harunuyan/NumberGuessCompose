package com.volie.numberguesscompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.volie.numberguesscompose.ui.theme.NumberGuessComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NumberGuessComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    NavigationPage()
                }
            }
        }
    }
}

@Composable
fun NavigationPage() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            Home(navController)
        }
        composable("guess") {
            Guess(navController)
        }
        composable("result/{result}", arguments = listOf(
            navArgument("result") { type = NavType.BoolType }
        )) {
            val result = it.arguments?.getBoolean("result")!!
            Result(navController, result)
        }
    }
}

@Composable
fun Home(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = "Number Guessing Game", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Image(
            painter = painterResource(id = R.drawable.ic_casino),
            contentDescription = "Description"
        )
        Button(
            onClick = { navController.navigate("guess") },
            Modifier.size(width = 250.dp, height = 50.dp)
        ) {
            Text(text = "Start Game")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    NumberGuessComposeTheme {

    }
}