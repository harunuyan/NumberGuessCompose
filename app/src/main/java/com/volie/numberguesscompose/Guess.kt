package com.volie.numberguesscompose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Guess(navController: NavController) {

    var tfGuess by remember { mutableStateOf("") }
    var chances by remember { mutableStateOf(5) }
    var tips by remember { mutableStateOf("") }
    var randomNumber by remember { mutableStateOf(0) }

    LaunchedEffect(key1 = true) {
        randomNumber = Random.nextInt(101)  // 0-101

    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = "Chances : $chances",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Red
        )
        Text(
            text = "Help : $tips",
            fontSize = 24.sp
        )
        TextField(
            value = tfGuess,
            onValueChange = { tfGuess = it },
            label = { Text(text = "Guess") })
        Button(
            onClick = {
                chances -= 1

                val guess = tfGuess.toInt()

                if (guess == randomNumber) {
                    navController.navigate("result/true") {
                        popUpTo("guess") { inclusive = true }
                    }
                    return@Button
                }
                if (guess > randomNumber) {
                    tips = "Decrease"
                }
                if (guess < randomNumber) {
                    tips = "Increase"
                }
                if (chances == 0) {
                    navController.navigate("result/false") {
                        popUpTo("guess") { inclusive = true }
                    }
                    return@Button
                }

                tfGuess = ""

            },
            Modifier.size(width = 250.dp, height = 50.dp)
        ) {
            Text(text = "GUESS")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun GuessPreview() {
}