package com.example.fakestore.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.fakestore.components.Header
import com.example.fakestore.components.ProductCard
import com.example.fakestore.models.ProductModel
import com.example.fakestore.services.ProductService
import com.example.fakestore.ui.theme.Background
import com.example.fakestore.ui.theme.FakeStoreTheme
import com.example.fakestore.ui.theme.routes.ProductDetailScreenRoute
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Composable
fun HomeScreen(nav: NavController){
    var products by remember { mutableStateOf(listOf<ProductModel>()) }
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
                service.getAllProduct()
            }
            products = result.await()
            loading = false
            Log.i("HomeScreen", "Si funciona")
        } catch (e: Exception) {
            loading = false
            Log.e("HomeScreen", e.toString())
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
        val banner = products.random()
        Column(modifier = Modifier
            .fillMaxSize()
            .background(Brush.linearGradient(listOf(Background, Color.White)))
            .padding(16.dp)
        ) {
            Header(Modifier
                .padding(start = 5.dp)
                .fillMaxWidth()
                .background(Background)
                .weight(3f), banner){
                nav.navigate(ProductDetailScreenRoute(banner.id))
            }

            Row(modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 10.dp)
                .fillMaxWidth()
                .weight(0.7f)
                .background(Color.Transparent),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft,null)
                Text("All Products",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                    lineHeight = 11.sp,
                    modifier = Modifier.weight(1f))
                Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight,null)
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
                    ProductCard(product) {
                        nav.navigate(ProductDetailScreenRoute(product.id))
                    }
                }
            }
        }
    }

}

@Preview
@Composable
fun HomeScreenPreview(){
    FakeStoreTheme {
        HomeScreen(rememberNavController())
    }
}
