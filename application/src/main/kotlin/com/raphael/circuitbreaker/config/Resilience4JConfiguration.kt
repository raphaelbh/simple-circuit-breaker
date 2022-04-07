package com.raphael.circuitbreaker.config

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig
import io.github.resilience4j.timelimiter.TimeLimiterConfig
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder
import org.springframework.cloud.client.circuitbreaker.Customizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Duration

@Configuration
class Resilience4JConfiguration {

    var circuitBreakerConfig = CircuitBreakerConfig.custom()
        .failureRateThreshold(5F)
        .waitDurationInOpenState(Duration.ofMillis(30000))
        .slidingWindowSize(5)
        .build()
    var timeLimiterConfig: TimeLimiterConfig = TimeLimiterConfig.custom()
        .timeoutDuration(Duration.ofSeconds(2))
        .build()
    
    @Bean
    fun globalCustomConfiguration(): Customizer<Resilience4JCircuitBreakerFactory> {

        return Customizer<Resilience4JCircuitBreakerFactory> { factory ->
            factory.configureDefault { id ->
                Resilience4JConfigBuilder(id)
                    .timeLimiterConfig(timeLimiterConfig)
                    .circuitBreakerConfig(circuitBreakerConfig)
                    .build()
            }
        }
    }

}