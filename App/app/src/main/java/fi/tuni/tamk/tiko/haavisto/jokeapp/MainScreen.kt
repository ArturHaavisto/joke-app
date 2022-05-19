package fi.tuni.tamk.tiko.haavisto.jokeapp

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import fi.tuni.tamk.tiko.haavisto.jokeapp.ui.theme.JokeAppTheme

// Text which is used to show default text, jokes and error messages.
val text = mutableStateOf("Press the JOKE button!")

// If true, joke card background color is turned to red.
val errorState = mutableStateOf(false)

@Composable
fun MainScreen() {
    JokeAppTheme {
        // Is used to hide keyboard when clicking outside of it.
        val localFocusManager = LocalFocusManager.current
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(
                modifier = Modifier.pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        localFocusManager.clearFocus()
                    })},
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.weight(1f, false)
                ) {
                    var cardColor = MaterialTheme.colors.secondary
                    if(errorState.value) {
                        cardColor = MaterialTheme.colors.error
                    }
                    Card(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState()),
                        backgroundColor = cardColor
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
                        modifier = Modifier
                            .weight(2f, true)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TextField(
                            value = textState.value,
                            onValueChange = { textState.value = it },
                            placeholder = {Text("Search joke by word/phrase")}
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f, true),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text("Allow following joke types:")
                    }

                    // States of switches
                    val nsfwCheckedState = remember { mutableStateOf(true) }
                    val racistCheckedState = remember { mutableStateOf(true) }
                    val religiousCheckedState = remember { mutableStateOf(true) }
                    val sexistCheckedState = remember { mutableStateOf(true) }
                    val politicalCheckedState = remember { mutableStateOf(true) }
                    val explicitCheckedState = remember { mutableStateOf(true) }

                    Row(
                        modifier = Modifier
                            .weight(4f, true)
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
                            .weight(4f, true)
                            .fillMaxWidth()
                            .padding(10.dp),
                        shape = RoundedCornerShape(15)
                    ) {
                        Text("JOKE")
                    }
                }
            }
        }
    }
}

/**
 * Function converts the joke arguments to proper form and calls an API function to get the joke.
 */
fun getJoke(flags: Map<String,Boolean>, input: String) {
    val flagList: MutableList<String>? = createFlagList(flags)
    var search: String? = null
    if(input != "") {search = input}
    fetchAndParse(flagList, search) {
        txt: String, isError: Boolean ->
        insertText(txt, isError)
    }
}

/**
 * Function takes a map of flag states and returns a list that shows every flag name.
 *
 * 'flags' map contains flag names and their indicators(true or false) as switch states. If the
 * switch state is false(disabled), it means that it is flagged. If there are no flags, null is
 * returned.
 */
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

/**
 * Function inserts either an error message or a joke to the main text. It also changes the error
 * state.
 */
fun insertText(txt: String, isError: Boolean = false) {
    text.value = txt
    if(isError) {
        errorState.value = true
    }
    else if(errorState.value) {
        errorState.value = false
    }
}