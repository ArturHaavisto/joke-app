package fi.tuni.tamk.tiko.haavisto.jokeapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fi.tuni.tamk.tiko.haavisto.jokeapp.ui.theme.JokeAppTheme

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
                        Text("joke will be shown here",
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
                        Button(onClick = { /*TODO*/ }) {
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