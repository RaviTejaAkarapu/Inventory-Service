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
        } catch(e : ClassNotFoundException) {
            throw ApplicationRuntimeException(500, "Class not found exception!", e.cause)
        }

        try {
            val url = "jdbc:postgresql://queenie.db.elephantsql.com:5432/wsguyqwl"
            val username = "wsguyqwl"
            val password = "ABLm7y-uATp1YR1e4hcIhlkmreyJq2AE"

            connection = DriverManager.getConnection(url, username, password)
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