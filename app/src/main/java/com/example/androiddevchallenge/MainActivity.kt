/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Build
import android.os.Bundle
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.data.Dog
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.view.DogDetailCard
import com.example.androiddevchallenge.ui.view.DogHome
import com.example.androiddevchallenge.utils.Navigator

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp(onBackPressedDispatcher)
            }
        }
    }
}

// Start building your app here!
@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun MyApp(backDispatcher: OnBackPressedDispatcher) {
    val dogs = mutableListOf<Dog>()
    dogs.add(Dog("草莓", 1, "德牧", R.drawable.border_collie, "这是德国牧羊犬～", "De mu"))
    dogs.add(Dog("娃娃", 1, "吉娃娃", R.drawable.chihuahua, "小可爱啊，这是！", "Ji wa waw"))
    dogs.add(Dog("短腿", 1, "柯基", R.drawable.corgi, "这短腿，爱了爱了～", "Ke ji"))
    dogs.add(Dog("斗士", 1, "法斗", R.drawable.french_bulldog, "怎么这么小～", "Fa dou"))
    dogs.add(Dog("拆家", 1, "哈士奇", R.drawable.husky, "知道我为什么叫拆家吗？", "Ha shi qi"))
    dogs.add(Dog("可爱", 1, "博美", R.drawable.pomeranian, "这.....", "Bo mei"))
    dogs.add(Dog("弟弟", 1, "八哥", R.drawable.pugs, "别看了，是我？", "Ba ge"))
    dogs.add(Dog("微笑天使", 1, "萨摩耶", R.drawable.samoyed, "雪橇三傻，萨摩耶？", "Sa mo ye"))
    
    val navigator: Navigator<Destination> = rememberSaveable(
        saver = Navigator.saver(backDispatcher)
    ) {
        Navigator(Destination.Home, backDispatcher)
    }
    val actions = remember(navigator) { Actions(navigator) }
    MaterialTheme {
        Scaffold(topBar = { TopAppBar() {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.CenterStart){
                Text(text = "DogWorld", textAlign = TextAlign.Start, fontSize = 20.sp)
            }

        }}) {
            Crossfade(navigator.current) {
                when(it){
                    Destination.Home -> DogHome(dogs = dogs, imgId = R.drawable.ic_hi, actions.selectSnack)
                    is Destination.DogDetail -> {
                        DogDetailCard(dog = dogs[it.pos])
                    }
                }
            }
        }
    }
}




@RequiresApi(Build.VERSION_CODES.N)
@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        // MyApp()
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        // MyApp()
    }
}
