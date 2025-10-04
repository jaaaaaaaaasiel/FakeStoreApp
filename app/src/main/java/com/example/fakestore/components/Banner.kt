package com.example.fakestore.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.fakestore.models.PMPrueba
import com.example.fakestore.ui.theme.ButtonBanner
import com.example.fakestore.ui.theme.TextColor

@Composable
fun Banner(banner: PMPrueba){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 35.dp, vertical = 15.dp)
    ) {
        Column(modifier = Modifier
            .weight(1f)
        ) {
            Text(banner.title,
                fontSize = 8.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 8.sp)
            Text(text= "${banner.description.take(200)}...",
                fontWeight = FontWeight.Light,
                fontSize = 7.sp,
                lineHeight = 7.sp,
                color = TextColor,
                modifier = Modifier.padding(top = 3.dp)
            )
            Box(modifier = Modifier
                .padding(top = 6.dp)
                .height(20.dp)
                .width(60.dp)
                .background(Color.Transparent)
                .border(width = 1.5.dp,
                    color = ButtonBanner),
                contentAlignment = Alignment.Center
            ) {
                Text("BUY $${banner.price}",
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Medium,
                    color = ButtonBanner)
            }
            Spacer(Modifier.weight(1f))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.KeyboardArrowDown,
                    "Details",
                    tint = Color.LightGray,
                    modifier = Modifier.size(9.dp))
                Text(text= "Product Details",
                    fontSize = 6.sp,
                    fontWeight = FontWeight.ExtraLight,
                    lineHeight = 7.sp,
                    modifier = Modifier.padding(start = 1.dp))
            }
        }
        AsyncImage(
            model = banner.image,
            contentDescription = banner.title,
            modifier = Modifier
                .padding(start = 15.dp)
                .weight(2f)
                .fillMaxHeight()
                .background(Color.LightGray),
            contentScale = ContentScale.Crop
        )
    }
}