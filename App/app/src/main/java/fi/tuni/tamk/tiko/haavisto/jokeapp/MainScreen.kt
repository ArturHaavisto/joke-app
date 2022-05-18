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
                    val nsfwCheckedState = remember { mutableStateOf(true) }
                    val racistCheckedState = remember { mutableStateOf(true) }
                    val religiousCheckedState = remember { mutableStateOf(true) }
                    val sexistCheckedState = remember { mutableStateOf(true) }
                    val politicalCheckedState = remember { mutableStateOf(true) }
                    val explicitCheckedState = remember { mutableStateOf(true) }

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
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = "nsfw")
                                Switch(
                                    checked = nsfwCheckedState.value,
                                    onCheckedChange = { nsfwCheckedState.value = it }
                                )
                            }
                            Column(
                                modifier = Modifier.padding(10.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = "racist")
                                Switch(
                                    checked = racistCheckedState.value,
                                    onCheckedChange = { racistCheckedState.value = it }
                                )
                            }
                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Column(
                                modifier = Modifier.padding(10.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = "religious")
                                Switch(
                                    checked = religiousCheckedState.value,
                                    onCheckedChange = { religiousCheckedState.value = it }
                                )
                            }
                            Column(
                                modifier = Modifier.padding(10.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = "sexist")
                                Switch(
                                    checked = sexistCheckedState.value,
                                    onCheckedChange = { sexistCheckedState.value = it }
                                )
                            }
                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Column(
                                modifier = Modifier.padding(10.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = "political")
                                Switch(
                                    checked = politicalCheckedState.value,
                                    onCheckedChange = { politicalCheckedState.value = it }
                                )
                            }
                            Column(
                                modifier = Modifier.padding(10.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = "explicit")
                                Switch(
                                    checked = explicitCheckedState.value,
                                    onCheckedChange = { explicitCheckedState.value = it }
                                )
                            }
                        }
                    }
                    Button(
                        onClick = { getJoke(
                            flags = mapOf(Pair("nsfw", nsfwCheckedState.value),
                                Pair("religious", religiousCheckedState.value),
                                Pair("political", politicalCheckedState.value),
                                Pair("racist", racistCheckedState.value),
                                Pair("sexist", sexistCheckedState.value),
                                Pair("explicit", explicitCheckedState.value)),
                            input = textState.value.text) },
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

fun getJoke(flags: Map<String,Boolean>, input: String) {
    val flagList: MutableList<String>? = createFlagList(flags)
    var search: String? = null
    if(input != "") {search = input}
    fetchAndParse(flagList, search) { insertText(it) }
}

fun createFlagList(flags: Map<String, Boolean>): MutableList<String>? {
    val stringFlags = mutableListOf<String>()
    var changeCheck = false
    flags.forEach {
        if(!it.value) {
            stringFlags.add(it.key)
            changeCheck = true
        }
    }
    return if(changeCheck) {
        stringFlags
    } else {
        null
    }
}

fun insertText(txt: String) {
    text.value = txt
}