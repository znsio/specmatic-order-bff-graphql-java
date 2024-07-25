package com.component.orders.config
import com.component.orders.models.DateScalar
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.graphql.execution.RuntimeWiringConfigurer

@Configuration
class GraphQLConfig {

    @Bean
    fun customScalars(): RuntimeWiringConfigurer {
        return  RuntimeWiringConfigurer { wiringBuilder ->
            wiringBuilder.scalar(DateScalar)
        }
    }
}
