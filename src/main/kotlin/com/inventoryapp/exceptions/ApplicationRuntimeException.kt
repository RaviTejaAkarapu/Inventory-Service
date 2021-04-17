package com.inventoryapp.exceptions

data class ApplicationRuntimeException(var errorCode: Int, var errorMessage: String, override val cause: Throwable?) : Exception(cause)