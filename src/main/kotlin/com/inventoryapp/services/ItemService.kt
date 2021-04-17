package com.inventoryapp.services

import com.inventoryapp.dao.ItemDao
import com.inventoryapp.entity.Item
import com.inventoryapp.exceptions.ApplicationRuntimeException
import com.inventoryapp.exceptions.InvalidInputException
import java.sql.Connection

class ItemService {

    private val itemDao = ItemDao()
    private var item: Item? = null

    @Throws(InvalidInputException::class, ApplicationRuntimeException::class)
    fun insert(connection: Connection, item: Item) {
        itemDao.insertItemToDB(connection, item)
    }

    @Throws(ApplicationRuntimeException::class, InvalidInputException::class)
    fun getItem(connection: Connection, itemToGet: String?): Item {
        item = itemDao.showItem(connection, itemToGet)
        item?.let {
            return it
        } ?: throw InvalidInputException(400, "Can't get item since item with this name is not present in database.")
    }

    @Throws(ApplicationRuntimeException::class)
    fun itemList(connection: Connection): List<Item> {
        return itemDao.getItemList(connection)
    }

}