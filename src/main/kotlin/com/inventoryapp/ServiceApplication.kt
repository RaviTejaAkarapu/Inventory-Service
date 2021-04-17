package com.inventoryapp

import com.inventoryapp.util.DatabaseConnector
import com.inventoryapp.util.LoggerInitializer
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.logging.Logger

@SpringBootApplication
class ServiceApplication

private val logger: Logger = Logger.getLogger("ServiceApplication")
private val dbConnector = DatabaseConnector()

fun main(args: Array<String>) {
    LoggerInitializer.initializeLogger()
    dbConnector.getConnection()

    runApplication<ServiceApplication>(*args)
}
