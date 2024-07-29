package com.example.artgallery

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artgallery.ui.theme.ArtGalleryTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import java.time.Year

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtGalleryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtGallery(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ArtGallery(modifier: Modifier = Modifier) {
    Column (Modifier.fillMaxWidth().fillMaxHeight(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Bottom) {
        Row (Modifier.padding(20.dp,50.dp)){

            val image = painterResource(id = R.drawable._88a90e67f8e2cc2_856)
            Image(painter = image, contentDescription = "art")

        }
        Row (Modifier.padding(20.dp, 20.dp)){

            CardInfo(artWorkTitle = "Still Life of Blue Rose and Other Flowers", artistInfo = "Owen Scott", year = 2021 )

        }
        Row {

            Row (Modifier.fillMaxWidth().padding(30.dp, 10.dp), horizontalArrangement = Arrangement.SpaceBetween){
                Button(onClick = { /*TODO*/ }, Modifier.width(130.dp)) {
                    Text(text = "Previous")
                }
                Button(onClick = { /*TODO*/ }, Modifier.width(130.dp)) {
                    Text(text = "Next")
                }
            }

        }
    }
}

@Composable
private fun CardInfo(artWorkTitle: String, artistInfo: String, year: Int, modifier: Modifier = Modifier){
    Column (modifier = modifier
        .background(Color(0x110066FF)),
        verticalArrangement = Arrangement.Center) {

        Row {
            Text(text = artWorkTitle, modifier = Modifier.padding(20.dp,20.dp,20.dp,2.dp), fontSize = 30.sp, fontWeight = FontWeight.W300, textAlign = TextAlign.Justify)

        }
        Row (horizontalArrangement = Arrangement.Start){
            Text(text = artistInfo, modifier = Modifier.padding(20.dp,2.dp,2.dp,20.dp), fontSize = 18.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
            Text(text = "($year)")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtGalleryTheme {
        ArtGallery()
    }
}