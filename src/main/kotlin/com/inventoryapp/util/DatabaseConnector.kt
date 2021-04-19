package com.inventoryapp.util

import com.inventoryapp.exceptions.ApplicationRuntimeException
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.util.logging.Logger

class DatabaseConnector {
    private var connection: Connection? = null

    companion object {
        private val logger: Logger = Logger.getLogger("DatabaseConnector")
    }

    @Throws(ApplicationRuntimeException::class)
    fun getConnection(): Connection {
        try {
            Class.forName("org.postgresql.Driver")
            connection = DriverManager.getConnection(
                "jdbc:postgresql://192.168.1.8:5432/inventorydb",
                "postgres",
                "interview123"
            )
        } catch (e: SQLException) {
            throw ApplicationRuntimeException(500, "Can't connect, SQLException!", e.cause)
        }
        connection?.let {
            logger.info("Successfully connected to Postgres Server!")
        } ?: run {
            logger.severe("Connection to postgres serve Failed!")
        }
        return connection!!
    }

    fun closeConnection(): Connection? {
        connection?.close()
        return connection
    }
}