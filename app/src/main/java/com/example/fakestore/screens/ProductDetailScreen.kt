package com.example.fakestore.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.fakestore.models.ProductModel
import com.example.fakestore.services.ProductService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun ProductDetailScreen(id: Int) {
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
            .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        Box(modifier = Modifier
            .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text(product?.title ?: "Error al cargar")
        }
    }

}