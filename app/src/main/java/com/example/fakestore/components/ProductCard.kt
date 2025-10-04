package com.example.fakestore.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.fakestore.models.PMPrueba
import com.example.fakestore.models.ProductModel
import com.example.fakestore.ui.theme.BackGroundCard
import com.example.fakestore.ui.theme.TextColor

@Composable
fun ProductCard(
    product: PMPrueba,
    onClick : () -> Unit
){
    Box(modifier = Modifier
        .height(140.dp)
        .background(BackGroundCard)
        .clickable(enabled = true, onClick = onClick),
        contentAlignment = Alignment.BottomEnd
    ) {
        AsyncImage(
            model = product.image,
            contentDescription = product.title,
            Modifier
                .fillMaxSize()
                .background(Color.White),
            contentScale = ContentScale.Crop
        )
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(35.dp)
            .clip(RoundedCornerShape(topStart = 5.dp, topEnd = 5.dp))
            .background(BackGroundCard)
            .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(text= "${product.title.take(15)}...",
                    fontWeight = FontWeight.Medium,
                    fontSize = 6.sp,
                    color = TextColor,
                    lineHeight = 6.sp
                )
                Text(product.category,
                    fontSize = 5.sp,
                    color = Color.LightGray,
                    lineHeight = 5.sp)
            }
            Text("$${product.price}",
                fontSize = 5.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 5.sp,
                color = TextColor)
        }

    }
}