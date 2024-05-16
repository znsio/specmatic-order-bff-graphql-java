package com.component.orders.backend

import com.component.orders.models.API
import com.component.orders.models.NewProduct
import com.component.orders.models.Product
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.client.SimpleClientHttpRequestFactory
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class OrderService {
    private val authToken = "API-TOKEN-SPEC"

    @Value("\${order.api}")
    lateinit var orderAPIUrl: String

    fun findProducts(type: String): List<Product> {
        val products = fetchProductsFromBackendAPI(type)
//        val producer = getKafkaProducer()
//        products.forEach {
//            val productMessage = ProductMessage(it.id, it.name, it.inventory)
//            producer.send(ProducerRecord(kafkaTopic, gson.toJson(productMessage)))
//        }
        return products
    }

    fun createProduct(newProduct: NewProduct): Product? {
        val apiUrl = orderAPIUrl + "/" + API.CREATE_PRODUCTS.url
        val headers = getHeaders()
        val requestEntity = HttpEntity(newProduct, headers)
        val response = RestTemplate().exchange(
            apiUrl,
            API.CREATE_PRODUCTS.method,
            requestEntity,
            String::class.java
        )
        if (response.body == null) {
            error("No product id received in Product API response.")
        }
        val productId = JSONObject(response.body).getInt("id")

        return Product(
            id = productId,
            name = newProduct.name,
            type = newProduct.type,
            inventory = newProduct.inventory ?: 0
        )
    }

    private fun getHeaders(): HttpHeaders {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        headers.set("Authenticate", authToken)
        return headers
    }

    private fun fetchProductsFromBackendAPI(type: String): List<Product> {
        val apiUrl = orderAPIUrl + "/" + API.LIST_PRODUCTS.url + "?type=$type"
        val restTemplate = RestTemplate()
        val requestFactory = SimpleClientHttpRequestFactory()
        requestFactory.setConnectTimeout(3000)
        requestFactory.setReadTimeout(3000)
        restTemplate.setRequestFactory(requestFactory)
        val response = restTemplate.getForEntity(apiUrl, List::class.java)
        return response.body.map {
            val product = it as Map<*, *>
            Product(
                product["name"].toString(),
                product["type"].toString(),
                product["inventory"].toString().toInt(),
                product["id"].toString().toInt(),
            )
        }
    }
}