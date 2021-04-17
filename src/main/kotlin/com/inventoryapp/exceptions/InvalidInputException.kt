package com.inventoryapp.exceptions

class InvalidInputException(var errorCode: Int, var errorMessage: String) : Exception()