package fi.tuni.tamk.tiko.haavisto.jokeapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
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
                    modifier = Modifier.weight(1f, false)
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
                Column(
                    modifier = Modifier.weight(1f, true),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    val textState = remember { mutableStateOf(TextFieldValue()) }
                    Column(
                        modifier = Modifier.weight(1f, true).fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TextField(
                            value = textState.value,
                            onValueChange = { textState.value = it }
                        )
                    }
                    Row(
                        modifier = Modifier
                            .weight(3f, true)
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Column(
                                modifier = Modifier.padding(10.dp),
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(text = "nsfw")
                                Text(text = "Switch")
                            }
                            Column(
                                modifier = Modifier.padding(10.dp),
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(text = "racist")
                                Text(text = "Switch")
                            }
                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Column(
                                modifier = Modifier.padding(10.dp),
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(text = "religious")
                                Text(text = "Switch")
                            }
                            Column(
                                modifier = Modifier.padding(10.dp),
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(text = "sexist")
                                Text(text = "Switch")
                            }
                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Column(
                                modifier = Modifier.padding(10.dp),
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(text = "political")
                                Text(text = "Switch")
                            }
                            Column(
                                modifier = Modifier.padding(10.dp),
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(text = "explicit")
                                Text(text = "Switch")
                            }
                        }
                    }
                    Button(
                        onClick = { getJoke(search = textState.value.text) },
                        modifier = Modifier
                            .weight(3f, true)
                            .fillMaxWidth()
                    ) {
                        Text("JOKE")
                    }
                }
            }
        }
    }
}


fun getJoke(search: String) {
    fetchAndParse(search = search) { insertText(it) }
}

fun insertText(txt: String) {
    text.value = txt
}