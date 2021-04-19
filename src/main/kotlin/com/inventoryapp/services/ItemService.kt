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
    fun insert(item: Item) {
        itemDao.insertItemToDB(item)
    }

    @Throws(ApplicationRuntimeException::class, InvalidInputException::class)
    fun getItem(itemToGet: String?): Item {
        item = itemDao.showItem(itemToGet)
        item?.let {
            return it
        } ?: throw InvalidInputException(400, "Can't get item since item with this name is not present in database.")
    }

    @Throws(ApplicationRuntimeException::class)
    fun itemList(): List<Item> {
        return itemDao.getItemList()
    }

}