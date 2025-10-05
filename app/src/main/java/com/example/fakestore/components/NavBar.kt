package com.example.fakestore.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun NavBar(onClick: () -> Unit){
    Row(modifier = Modifier
        .fillMaxWidth()
    ) {
        Icon(imageVector = Icons.Default.Menu,
            "Menu",
            modifier = Modifier.weight(1f))
        ButtonBack(Modifier
            .weight(1f)
            .clickable(enabled = true, onClick = onClick))
        Text(text= "Fake Store",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(6f),
            textAlign = TextAlign.Center)
        ButtonCart(Modifier.weight(1f))
        ButtonLan(Modifier.weight(1f))
    }
}