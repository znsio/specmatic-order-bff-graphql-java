package com.component.orders.models

import org.springframework.http.HttpMethod

enum class API(val method: HttpMethod, val url: String) {
    LIST_PRODUCTS(HttpMethod.GET, "/products"),
    CREATE_PRODUCTS(HttpMethod.POST, "/products")
}