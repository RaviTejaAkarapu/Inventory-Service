package com.inventoryapp.dao

import com.inventoryapp.entity.Item
import com.inventoryapp.exceptions.ApplicationRuntimeException
import com.inventoryapp.util.DatabaseConnector
import java.sql.Connection
import java.sql.ResultSet
import java.sql.SQLException
import java.util.*

class ItemDao {
    private lateinit var resultSet: ResultSet
    var connector: DatabaseConnector = DatabaseConnector()
    var connection = connector.getConnection()

    @Throws(ApplicationRuntimeException::class)
    fun showItem(itemToGet: String?): Item? {
        return try {
            val showQuery = "select * from item where sku_id = ?"
            val showStatement = connection.prepareStatement(showQuery)
            showStatement.setString(1, itemToGet)
            resultSet = showStatement.executeQuery()
            var currentItem: Item? = Item()
            while (resultSet.next()) {
                val skuId: String = resultSet.getString(1)
                val itemName: String = resultSet.getString(2)
                val manufacturerName: String = resultSet.getString(3)
                val quantity: Int = resultSet.getInt(4)
                currentItem = Item(skuId, itemName, manufacturerName, quantity)
            }
            currentItem
        } catch (e: SQLException) {
            throw ApplicationRuntimeException(500, "Couldn't find item in DB!", e.cause)
        }
    }

    @Throws(ApplicationRuntimeException::class)
    fun insertItemToDB(item: Item) {
        try {
            val insertionQuery = "insert into item (sku_id,item_name,manufacturer_name,quantity) values(?,?,?,?)"
            val insertItemStatement = connection.prepareStatement(insertionQuery)
            insertItemStatement.apply {
                setString(1, item.skuId)
                setString(2, item.itemName)
                setString(3, item.manufacturerName)
                setInt(4, item.quantity ?: 0)
            }.executeUpdate()
        } catch (e: SQLException) {
            throw ApplicationRuntimeException(500, "Couldn't insert item in DB!", e.cause)
        }
    }

    @Throws(ApplicationRuntimeException::class)
    fun getItemList(): List<Item> {
        return try {
            val fetchStatement = connection.prepareStatement("select * from item;")
            resultSet = fetchStatement.executeQuery()
            val itemsList: MutableList<Item> = ArrayList<Item>()
            while (resultSet.next()) {
                val skuId = resultSet.getString(1)
                val itemName = resultSet.getString(2)
                val manufacturerName = resultSet.getString(3)
                val quantity = resultSet.getInt(4)
                val item = Item(skuId, itemName, manufacturerName, quantity)
                itemsList.add(item)
            }
            itemsList
        } catch (e: SQLException) {
            throw ApplicationRuntimeException(500, "Items in order couldn't be reached.", e.cause)
        }
    }
}