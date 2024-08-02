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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    var actualPicture by remember {
        mutableStateOf(0)
    }
    val pictures = listOf(
        Picture(R.drawable.ouch_7726461_1920, "Glass sphere", "bjornbrathen", "2024"),
        Picture(R.drawable.ai_generated_8350066_1920, "Cool Monkey","InspiredImages","2023"),
        Picture(R.drawable.ai_generated_8602017_1920, "Drunk Bear","nvd9612","2024")
    )

    Column (
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Bottom) {
        Row (Modifier.padding(20.dp,50.dp)){

            val image = painterResource(pictures[actualPicture].pictureReference)
            Image(painter = image, contentDescription = "art")

        }
        Row (Modifier.padding(20.dp, 20.dp)){

            CardInfo(artWorkTitle = pictures[actualPicture].title, artistInfo = pictures[actualPicture].artist, year = pictures[actualPicture].year )

        }
        Row {

            Row (
                Modifier
                    .fillMaxWidth()
                    .padding(30.dp, 10.dp), horizontalArrangement = Arrangement.SpaceBetween){
                Button(onClick = { actualPicture = (actualPicture - 1 + pictures.size) % pictures.size }, Modifier.width(130.dp)) {
                    Text(text = "Previous")
                }
                Button(onClick = { actualPicture = (actualPicture + 1) % pictures.size }, Modifier.width(130.dp)) {
                    Text(text = "Next")
                }
            }

        }
    }
}

@Composable
private fun CardInfo(artWorkTitle: String, artistInfo: String, year: String, modifier: Modifier = Modifier){
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

data class Picture(val pictureReference: Int, val title: String,val artist: String,val year: String)


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtGalleryTheme {
        ArtGallery()
    }
}

