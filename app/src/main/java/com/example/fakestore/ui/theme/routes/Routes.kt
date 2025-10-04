package com.example.fakestore.ui.theme.routes

import kotlinx.serialization.Serializable

@Serializable
object HomeScreenRoute

@Serializable
data class ProductDetailScreenRoute(val id: Int)