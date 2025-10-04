package com.example.fakestore.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.fakestore.models.PMPrueba
@Composable
fun Header(mod: Modifier, banner: PMPrueba){
    Column(modifier = mod) {
        NavBar()
        Banner(banner)
    }
}