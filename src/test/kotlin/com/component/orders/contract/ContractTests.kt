package com.component.orders.contract

import io.specmatic.graphql.VersionInfo.describe
import io.specmatic.graphql.test.SpecmaticGraphQLContractTest
import io.specmatic.stub.ContractStub
import io.specmatic.stub.createStub
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT

@SpringBootTest(webEnvironment = DEFINED_PORT)
class ContractTests : SpecmaticGraphQLContractTest {
    companion object {
        private var httpStub: ContractStub? = null
        private const val APPLICATION_HOST = "localhost"
        private const val APPLICATION_PORT = "8080"
        private const val HTTP_STUB_HOST = "localhost"
        private const val HTTP_STUB_PORT = 8090

        @JvmStatic
        @BeforeAll
        fun setUp() {
            println("Testing using Specmatic GraphQL " + describe())
            System.setProperty("host", APPLICATION_HOST)
            System.setProperty("port", APPLICATION_PORT)

            // Start Specmatic Http Stub and set the expectations
            httpStub = createStub(listOf("./src/test/resources/expectations"), HTTP_STUB_HOST, HTTP_STUB_PORT)

            System.setProperty("SPECMATIC_GENERATIVE_TESTS", "true")
        }

        @JvmStatic
        @AfterAll
        fun tearDown() {
            // Shutdown Specmatic Http Stub
            httpStub?.close()
        }
    }
}
