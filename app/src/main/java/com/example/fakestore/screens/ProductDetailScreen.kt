package com.example.fakestore.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.fakestore.components.NavBar
import com.example.fakestore.models.ProductModel
import com.example.fakestore.services.ProductService
import com.example.fakestore.ui.theme.Background
import com.example.fakestore.ui.theme.ButtonBanner
import com.example.fakestore.ui.theme.TextColor
import com.example.fakestore.ui.theme.routes.HomeScreenRoute
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun ProductDetailScreen(id: Int, nav: NavController) {
    var product by remember { mutableStateOf<ProductModel?>(null) }
    var loading by remember { mutableStateOf(true) }

    LaunchedEffect(true) {
        try {
            //1. Crear una instancia de RetroFit
            val retrofit = Retrofit
                .Builder()
                .baseUrl("https://fakestoreapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service = retrofit.create(ProductService::class.java)
            val result = async(Dispatchers.IO) {
                service.getProductById(id)
            }
            product = result.await()
            loading = false
            Log.i("ProductDetailScreen", "Si funciona")
        } catch (e: Exception) {
            loading = false
            Log.e("ProductDetailScreen", e.toString())
        }
    }
    if (loading){
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Background),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(16.dp)
        ) {
            NavBar{nav.navigate(HomeScreenRoute)}
            Text(product?.title ?: "Error al cargar producto",
                fontWeight = FontWeight.Bold,
                color = TextColor,
                lineHeight = 18.sp,
                modifier = Modifier
                    .padding(top = 30.dp, start = 10.dp)
                    .width(210.dp)
            )
            Text(product?.category ?: "",
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 5.dp, start = 10.dp)
            )
            AsyncImage(
                model = product?.image ?: "",
                contentDescription = product?.title ?: "",
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 20.dp)
                    .fillMaxWidth()
                    .weight(12f)
                    .background(Color.LightGray),
                contentScale = ContentScale.Crop
            )
            Text(product?.description ?: "",
                fontSize = 12.sp,
                color = TextColor,
                lineHeight = 13.sp,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            Spacer(Modifier.weight(1f))
            Text("BUY NOW $${product?.price}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Black,
                textAlign = TextAlign.Center,
                color = ButtonBanner,
                modifier = Modifier
                    .padding(horizontal = 15.dp, vertical = 5.dp)
                    .fillMaxWidth()
                    .clip(CircleShape)
                    .background(Color.White)
                    .border(width = 2.dp, color = ButtonBanner)
                    .padding(5.dp)
            )


        }
    }

}
