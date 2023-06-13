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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Guess(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        val tfGuess = remember { mutableStateOf("") }

        Text(
            text = "Chances : 5",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Red
        )
        Text(
            text = "Help : Increase",
            fontSize = 24.sp
        )
        TextField(
            value = tfGuess.value,
            onValueChange = { tfGuess.value = it },
            label = { Text(text = "Guess") })
        Button(
            onClick = {
                navController.navigate("result") {
                    popUpTo("guess") { inclusive = true }
                }

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