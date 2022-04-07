package com.raphael.circuitbreaker.controller

import com.raphael.circuitbreaker.services.IntegrationService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/circuit-breaker")
class CircuitBreakerController(
    private val integrationService: IntegrationService
) {
    @GetMapping
    fun circuitBreaker(@RequestParam(required = true) delay: Long): String = integrationService.process(delay)
}