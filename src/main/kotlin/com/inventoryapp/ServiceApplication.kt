package com.inventoryapp

import com.inventoryapp.util.DatabaseConnector
import com.inventoryapp.util.LoggerInitializer
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ServiceApplication

private val dbConnector = DatabaseConnector()

fun main(args: Array<String>) {
    LoggerInitializer.initializeLogger()
    dbConnector.getConnection()

    runApplication<ServiceApplication>(*args)
}
