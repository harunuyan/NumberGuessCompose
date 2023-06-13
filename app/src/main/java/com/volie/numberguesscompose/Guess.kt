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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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

    val tfGuess = remember { mutableStateOf("") }
    val chances = remember { mutableStateOf(5) }
    val tips = remember { mutableStateOf("") }
    val randomNumber = remember { mutableStateOf(0) }

    LaunchedEffect(key1 = true) {
        randomNumber.value = Random.nextInt(101)  // 0-101

    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = "Chances : ${chances.value}",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Red
        )
        Text(
            text = "Help : ${tips.value}",
            fontSize = 24.sp
        )
        TextField(
            value = tfGuess.value,
            onValueChange = { tfGuess.value = it },
            label = { Text(text = "Guess") })
        Button(
            onClick = {
                chances.value = chances.value - 1

                val guess = tfGuess.value.toInt()

                if (guess == randomNumber.value) {
                    navController.navigate("result/true") {
                        popUpTo("guess") { inclusive = true }
                    }
                    return@Button
                }
                if (guess > randomNumber.value) {
                    tips.value = "Decrease"
                }
                if (guess < randomNumber.value) {
                    tips.value = "Increase"
                }
                if (chances.value == 0) {
                    navController.navigate("result/false") {
                        popUpTo("guess") { inclusive = true }
                    }
                    return@Button
                }

                tfGuess.value = ""

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