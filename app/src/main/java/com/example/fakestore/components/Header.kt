package com.example.fakestore.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.fakestore.models.ProductModel

@Composable
fun Header(mod: Modifier, banner: ProductModel){
    Column(modifier = mod) {
        NavBar{}
        Banner(banner)
    }
}