package com.inventoryapp.controllers

import com.inventoryapp.entity.Item
import com.inventoryapp.exceptions.ApplicationRuntimeException
import com.inventoryapp.exceptions.InvalidInputException
import com.inventoryapp.model.ErrorResponse
import com.inventoryapp.model.GetItemRequest
import com.inventoryapp.services.ItemService
import com.inventoryapp.util.DatabaseConnector
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/item")
class ItemController {

    private val itemService = ItemService()
    var connector: DatabaseConnector = DatabaseConnector()

    @PostMapping("/getItem")
    fun getItem(@RequestBody getItemRequest: GetItemRequest): ResponseEntity<*>? {
        return try {
            val itemToGet = GetItemRequest(getItemRequest.skuId)
            val item: Item = itemService.getItem(connector.getConnection(), itemToGet.skuId)
            ResponseEntity<Any?>(item, HttpStatus.OK)
        } catch (e: InvalidInputException) {
            val error = ErrorResponse(e.errorCode, e.errorMessage)
            ResponseEntity<Any?>(error, HttpStatus.BAD_REQUEST)
        } catch (e: ApplicationRuntimeException) {
            val error = ErrorResponse(e.errorCode, e.errorMessage)
            ResponseEntity<Any?>(error, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping("/addItem")
    fun addItem(@RequestBody item: Item): ResponseEntity<*>? {
        return try {
            itemService.insert(connector.getConnection(), item)
            ResponseEntity.ok("Inserted the new item successfully.")
        } catch (e: InvalidInputException) {
            val error = ErrorResponse(e.errorCode, e.errorMessage)
            ResponseEntity<Any?>(error, HttpStatus.BAD_REQUEST)
        } catch (e: ApplicationRuntimeException) {
            val error = ErrorResponse(e.errorCode, e.errorMessage)
            ResponseEntity<Any?>(error, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/getItems")
    fun getItems(): ResponseEntity<*>? {
        return try {
            val listOfItems: List<Item> = itemService.itemList(connector.getConnection())
            ResponseEntity<Any?>(listOfItems, HttpStatus.OK)
        } catch (e: ApplicationRuntimeException) {
            val error = ErrorResponse(e.errorCode, e.errorMessage)
            ResponseEntity<Any?>(error, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}