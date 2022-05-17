package fi.tuni.tamk.tiko.haavisto.jokeapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import fi.tuni.tamk.tiko.haavisto.jokeapp.ui.theme.JokeAppTheme

@Composable
fun MainScreen() {
    JokeAppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Text("test")
        }
    }
}