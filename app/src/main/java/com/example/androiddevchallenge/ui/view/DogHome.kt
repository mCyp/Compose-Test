package com.example.androiddevchallenge.ui.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.R
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.data.Dog

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun DogHome(dogs: List<Dog>, imgId: Int, onSelect: (Int) -> Unit){
    val scrollState = rememberLazyListState()
    LazyColumn(
        state = scrollState
    ) {
        val grouped = dogs.groupBy { it.pinyin[0] }
        grouped.forEach { t, curDogs ->
            item {
                DogHeader(str = t.toString())
            }
            itemsIndexed(curDogs) {i, dog ->
                val dogItemModify = Modifier
                    .padding(top = 16.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
                    .clickable { onSelect(i) }
                DogCard(dog = dog, imgId = imgId, modifier = dogItemModify)
            }
        }
    }
}

@Composable
fun DogHeader(str: String) {
    Box() {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            backgroundColor = Color(0xFFD4E6F1),
            elevation = 3.dp,
            shape = RoundedCornerShape(0.dp)
        ) {
            Text(
                text = str,
                color = Color.Blue,
                fontSize = 14.sp,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 2.dp)
            )
        }
    }
}

@Composable
fun DogCard(dog: Dog, imgId: Int, modifier: Modifier) {
    Row(
        modifier = modifier) {
        Surface(
            modifier = Modifier.size(50.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {
            Image(
                bitmap = ImageBitmap.imageResource(id = dog.img),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text(dog.name, fontWeight = FontWeight.Bold)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(dog.breed, style = MaterialTheme.typography.body2)
            }
        }
        Box(
            Modifier
                .fillMaxSize()
                .align(Alignment.CenterVertically),
            contentAlignment = Alignment.CenterEnd
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = imgId),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.tint(Color(0xFFA569BD)),
                alignment = Alignment.CenterEnd
            )
        }
    }
}