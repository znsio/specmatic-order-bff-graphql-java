package com.component.orders.contract

import com.component.orders.Application
import `in`.specmatic.graphql.test.SpecmaticGraphQLContractTest
import `in`.specmatic.stub.ContractStub
import `in`.specmatic.stub.createStub
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.springframework.boot.SpringApplication
import org.springframework.context.ConfigurableApplicationContext

class GraphQLContractTests : SpecmaticGraphQLContractTest {

    companion object {
        private var context: ConfigurableApplicationContext? = null
        private var httpStub: ContractStub? = null
        private const val APPLICATION_HOST = "localhost"
        private const val APPLICATION_PORT = "8080"
        private const val HTTP_STUB_HOST = "localhost"
        private const val HTTP_STUB_PORT = 8090

        @JvmStatic
        @BeforeAll
        fun setUp() {
            System.setProperty("host", APPLICATION_HOST)
            System.setProperty("port", APPLICATION_PORT)

            // Start Specmatic Http Stub and set the expectations
            httpStub = createStub(listOf("./src/test/resources/expectations"), HTTP_STUB_HOST, HTTP_STUB_PORT)

            // Start Springboot application
            val springApp = SpringApplication(Application::class.java)
            context = springApp.run()
        }

        @JvmStatic
        @AfterAll
        fun tearDown() {
            // Shutdown Springboot application
            context?.close()

            // Shutdown Specmatic Http Stub
            httpStub?.close()
        }
    }
}
