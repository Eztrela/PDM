package com.example.googlecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.googlecompose.ui.theme.GoogleComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GoogleComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    TutorialJetpack()
//                    AllDone()
                    ScreenQuadrants()
                }
            }
        }
    }
}

@Composable
fun TutorialJetpack( modifier: Modifier = Modifier) {
    Column {
        val image = painterResource(id = R.drawable.ic_task_completed)
        Image(painter = image, contentDescription = "imagem doidera", modifier=modifier.fillMaxWidth())
        Text(
            text = "Jetpack Compose tutorial",
            modifier = modifier.padding(24.dp),
            fontSize = 24.sp
        )
        Text(
            text = "Jetpack Compose is a modern toolkit for building native Android UI. Compose simplifies and accelerates UI development on Android with less code, powerful tools, and intuitive Kotlin APIs.\n",
            modifier = modifier.padding(horizontal =16.dp),
            textAlign = TextAlign.Justify
        )
        Text(
            text = "In this tutorial, you build a simple UI component with declarative functions. You call Compose functions to say what elements you want and the Compose compiler does the rest. Compose is built around Composable functions. These functions let you define your app\\'s UI programmatically because they let you describe how it should look and provide data dependencies, rather than focus on the process of the UI\\'s construction, such as initializing an element and then attaching it to a parent. To create a Composable function, you add the @Composable annotation to the function name.\n",
            modifier = modifier.padding(16.dp),
            textAlign = TextAlign.Justify
        )

    }
}

@Composable
fun AllDone(modifier: Modifier = Modifier){
    Column(modifier = modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        val image = painterResource(id = R.drawable.bg_compose_background)
        Image(painter = image, contentDescription = "doneImage")
        Text(text = "All tasks Completed", modifier = modifier.padding(top = 24.dp, bottom = 8.dp), fontWeight = FontWeight.Bold)
        Text(text = "Nice work!", fontSize = 16.sp)
    }
}

@Composable
fun ScreenQuadrants(){
    Column(Modifier.fillMaxWidth()) {
        Row (Modifier.weight(1f)){
            ComposableCard(card_title = "Text composable", card_description = "Displays text and follows the recommended Material Design guidelines.\n", card_background = Color(0xFFEADDFF), modifier = Modifier.weight(1f))
            ComposableCard(card_title = "Image composable", card_description = "Creates a composable that lays out and draws a given Painter class object.", card_background = Color(0xFFD0BCFF), modifier = Modifier.weight(1f))
        }
        Row (Modifier.weight(1f)){
            ComposableCard(card_title = "Row composable", card_description = "A layout composable that places its children in a horizontal sequence.", card_background = Color(0xFFB69DF8), modifier = Modifier.weight(1f))
            ComposableCard(card_title = "Column composable", card_description = "A layout composable that places its children in a vertical sequence.", card_background = Color(0xFFF6EDFF), modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun ComposableCard( card_title: String, card_description: String, card_background: Color, modifier: Modifier = Modifier){
    Column(modifier = modifier
        .background(card_background)
        .padding(16.dp)
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = card_title,
            modifier = modifier.padding(bottom = 16.dp),
            fontWeight = FontWeight.Bold)
        Text(text = card_description,
            textAlign = TextAlign.Justify)

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GoogleComposeTheme {
//        TutorialJetpack()
        ScreenQuadrants()
    }
}