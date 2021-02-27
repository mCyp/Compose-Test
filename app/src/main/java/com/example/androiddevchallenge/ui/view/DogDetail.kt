package com.example.androiddevchallenge.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.data.Dog

@Composable
fun DogDetailCard(dog: Dog) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        val imageModifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(shape = RoundedCornerShape(4.dp))
        Image(
            bitmap = ImageBitmap.imageResource(id = dog.img),
            contentDescription = null,
            modifier = imageModifier,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        val textModifier = Modifier.padding(8.dp).fillMaxWidth()
        Text(text = dog.name, modifier = textModifier, color = Color.Black, fontSize = 18.sp, textAlign = TextAlign.Start)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = dog.breed, modifier = textModifier, color = Color.Black, fontSize = 14.sp, textAlign = TextAlign.Start)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = dog.breedDesc, modifier = Modifier.fillMaxSize().padding(8.dp), color = Color.Black, fontSize = 14.sp, fontStyle = FontStyle.Italic)
    }
}