package com.component.orders.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class CorsConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("*")  // Allows all origins
            .allowedMethods("*")  // Allows all HTTP methods
            .allowedHeaders("*")  // Allows all headers
    }
}
