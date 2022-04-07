package com.raphael.circuitbreaker.services

import org.springframework.cloud.client.circuitbreaker.CircuitBreaker
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory
import org.springframework.stereotype.Service

@Service
class IntegrationService(
    private val circuitBreakerFactory: CircuitBreakerFactory<*, *>,) {

    fun process(delay: Long): String {
        val circuitBreaker: CircuitBreaker = circuitBreakerFactory.create("circuitBreaker")

        return circuitBreaker.run({
            execute(delay)
        }, { fallback() })
    }

    private fun execute(delay: Long): String {
        if (delay < 1) throw Exception("Invalid delay value")

        Thread.sleep(delay)
        return "{\"data\": \"integration_content\"}"
    }

    private fun fallback(): String = "{\"error\": \"Internal Server Error\"}"
}