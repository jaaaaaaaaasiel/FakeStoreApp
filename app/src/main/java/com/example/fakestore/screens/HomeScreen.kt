package com.example.fakestore.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.fakestore.components.ProductCard
import com.example.fakestore.models.ProductModel
import com.example.fakestore.services.ProductService
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
            .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(modifier = Modifier
            .fillMaxSize()
        ) {
            items(products) { product ->
                ProductCard(
                    product= product,
                    onClick = {
                        nav.navigate(ProductDetailScreenRoute(product.id))
                    }
                )
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