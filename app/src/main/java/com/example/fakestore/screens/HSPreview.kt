package com.example.fakestore.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fakestore.components.ButtonBack
import com.example.fakestore.components.ButtonCart
import com.example.fakestore.components.ButtonLan
import com.example.fakestore.components.Header
import com.example.fakestore.models.products
import com.example.fakestore.ui.theme.BackGroundCard
import com.example.fakestore.ui.theme.Background
import com.example.fakestore.ui.theme.FakeStoreTheme

@Composable
fun HSPreview(){
    var banner by remember { mutableStateOf(products.random()) }
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Brush.linearGradient(listOf(Background, Color.White)))
        .padding(16.dp)
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .background(Background)
            .weight(3f)
        ) {
            Header()
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 35.dp, vertical = 15.dp)
            ) {
                Column(modifier = Modifier
                    .weight(1f)
                ) {
                    Text(banner.title,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold)
                }
                Box(modifier = Modifier
                    .weight(2f)
                )
            }

        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .padding(horizontal = 40.dp, vertical = 10.dp)
                .fillMaxSize()
                .background(Color.White)
                .weight(8f),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            items(products) { product ->
                Box(
                    modifier = Modifier
                        .height(140.dp)
                        .background(BackGroundCard),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Item ${product.id}")
                }
            }
        }
    }

}

@Composable
@Preview
fun Preview(){
    FakeStoreTheme {
        HSPreview()
    }
}