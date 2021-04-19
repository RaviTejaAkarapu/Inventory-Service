package com.inventoryapp.controllers

import com.inventoryapp.exceptions.ApplicationRuntimeException
import com.inventoryapp.model.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class HealthCheckController {
    @GetMapping("/healthcheck")
    fun healthCheck(): ResponseEntity<*>? {
        return try {
            ResponseEntity<Any?>(true, HttpStatus.OK)
        } catch (e: ApplicationRuntimeException) {
            val error = ErrorResponse(e.errorCode, e.errorMessage)
            ResponseEntity<Any?>(error, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}