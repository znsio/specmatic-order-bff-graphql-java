package com.component.orders.controllers

import com.component.orders.backend.OfferService
import com.component.orders.models.NewProduct
import com.component.orders.models.Offer
import com.component.orders.models.Product
import com.component.orders.services.OrderBFFService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import java.util.*


@Controller
class Products(
    @Autowired val orderBFFService: OrderBFFService,
    @Autowired val offerService: OfferService
) {
    @QueryMapping
    fun findAvailableProducts(@Argument type: String, @Argument pageSize: Int?): List<Product> {
        if (pageSize != null && pageSize < 0) throw IllegalArgumentException("pageSize must be positive")
        return orderBFFService.findProducts(type)
    }

    @MutationMapping
    fun createProduct(@Argument newProduct: NewProduct): Product? {
        return orderBFFService.createProduct(newProduct)
    }

    @QueryMapping
    fun findOffersForDate(@Argument date: Date): List<Offer> {
        return offerService.findOfferForDate(date)
    }
}

