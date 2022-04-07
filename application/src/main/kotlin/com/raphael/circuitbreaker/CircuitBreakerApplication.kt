package com.raphael.circuitbreaker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CircuitBreakerApplication

fun main(args: Array<String>) {
	runApplication<CircuitBreakerApplication>(*args)
}