package fi.tuni.tamk.tiko.haavisto.jokeapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fi.tuni.tamk.tiko.haavisto.jokeapp.ui.theme.JokeAppTheme

val text = mutableStateOf("Joke will be shown here")

@Composable
fun MainScreen() {
    JokeAppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.weight(3f, false)
                ) {
                    Card(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState()),
                        backgroundColor = MaterialTheme.colors.primary
                    ) {
                        val myText by text
                        Text(myText,
                        modifier = Modifier.padding(10.dp))
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .weight(1f, false)
                ) {
                    Column() {
                        Button(onClick = { /*TODO*/ }) {
                            Text("search")
                        }
                    }
                    Column() {
                        Button(onClick = { getJoke() }) {
                            Text("JOKE")
                        }
                    }
                    Column() {
                        Button(onClick = { /*TODO*/ }) {
                            Text("flags")
                        }
                    }
                }
            }
        }
    }
}


fun getJoke() {
    fetchAndParse { insertText(it) }
}

fun insertText(txt: String) {
    text.value = txt
}