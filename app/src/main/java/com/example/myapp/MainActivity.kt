package com.example.myapp

import android.graphics.drawable.shapes.Shape
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapp.ui.theme.MyAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ButtonExamples()
                }
            }
        }
    }
}

@Composable
fun ButtonExamples() {
    Column(
        modifier = Modifier
            .padding(48.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Filled button:")
        FilledButtonExample(onClick = { Log.d("Filled button", "Filled button clicked.") })
        Text("Filled tonal button:")
        FilledTonalButtonExample(onClick = { Log.d("Filled tonal button", "Filled tonal button clicked.") })
        Text("Elevated button:")
        ElevatedButtonExample(onClick = { Log.d("Elevated button", "Elevated button clicked.") })
        Text("Outlined button:")
        OutlinedButtonExample(onClick = { Log.d("Outlined button", "Outlined button clicked.") })
        Text("Text button")
        TextButtonExample(onClick = { Log.d("Text button", "Text button clicked.") })
    }
}

// [START android_compose_components_filledbutton]
@Composable
fun FilledButtonExample(onClick: () -> Unit) {
    Button(onClick = { onClick() }, enabled = false) {
        Text("Filled disabled")
    }
    Button(onClick = { onClick() }, enabled = true) {
        Text("Filled enabled")
    }
}
// [END android_compose_components_filledbutton]

// [START android_compose_components_filledtonalbutton]
@Composable
fun FilledTonalButtonExample(onClick: () -> Unit) {
    FilledTonalButton(onClick = { onClick() }, shape = ShapeDefaults.ExtraSmall, colors = ButtonDefaults.filledTonalButtonColors(
        Color(0xFFAA00FF))) {
        Text("Tonal", color = Color.White)
    }
}
// [END android_compose_components_filledtonalbutton]

// [START android_compose_components_elevatedbutton]
@Composable
fun ElevatedButtonExample(onClick: () -> Unit) {
    ElevatedButton(onClick = { onClick() }, colors = ButtonDefaults.elevatedButtonColors(Color(0xFFAA00FF), Color.White, Color(0x33AA00FF)),) {
        Text("Elevated enabled")
    }
    ElevatedButton(onClick = { onClick() }, colors = ButtonDefaults.elevatedButtonColors(Color(0xFFAA00FF), Color.White, Color(0x33AA00FF)), enabled = false) {
        Text("Elevated disabled")
    }
}
// [END android_compose_components_elevatedbutton]

// [START android_compose_components_outlinedbutton]
@Composable
fun OutlinedButtonExample(onClick: () -> Unit) {
    OutlinedButton(onClick = { onClick() }) {
        Text("Outlined")
    }
}
// [END android_compose_components_outlinedbutton]

// [START android_compose_components_textbutton]
@Composable
fun TextButtonExample(onClick: () -> Unit) {
    TextButton(
        onClick = { onClick() }
    ) {
        Text("Text Button")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyAppTheme {
        ButtonExamples()
    }
}