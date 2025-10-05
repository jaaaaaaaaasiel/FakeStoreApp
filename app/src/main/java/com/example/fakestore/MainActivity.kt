package com.example.fakestore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.fakestore.screens.HomeScreen
import com.example.fakestore.screens.ProductDetailScreen
import com.example.fakestore.ui.theme.FakeStoreTheme
import com.example.fakestore.ui.theme.routes.HomeScreenRoute
import com.example.fakestore.ui.theme.routes.ProductDetailScreenRoute

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FakeStoreTheme {
                val nav = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        nav,
                        HomeScreenRoute
                    ) {
                        composable<HomeScreenRoute> {
                            HomeScreen(nav)
                        }
                        composable<ProductDetailScreenRoute> { backStack ->
                            val args = backStack.toRoute<ProductDetailScreenRoute>()
                            ProductDetailScreen(args.id, nav)
                        }
                    }
                }
            }
        }
    }
}
