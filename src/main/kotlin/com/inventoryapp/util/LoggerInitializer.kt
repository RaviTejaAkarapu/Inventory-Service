package com.inventoryapp.util

import com.inventoryapp.ServiceApplication
import java.io.IOException
import java.io.InputStream
import java.util.logging.LogManager
import java.util.logging.Logger

object LoggerInitializer {
    private val logger = Logger.getLogger("LoggerInitializer")
    fun initializeLogger() {
        val manager = LogManager.getLogManager()
        val stream = ServiceApplication::class.java.classLoader.getResourceAsStream(
            "logging.properties"
        )
        readConfiguration(manager, stream)
        logger.info("Logger has been Initialized!")
    }

    fun readConfiguration(manager: LogManager, stream: InputStream) {
        try {
            manager.readConfiguration(stream)
        } catch (e: IOException) {
            e.message
        } finally {
            try {
                stream.close()
            } catch (e: IOException) {
                e.message
            }
        }
    }
}