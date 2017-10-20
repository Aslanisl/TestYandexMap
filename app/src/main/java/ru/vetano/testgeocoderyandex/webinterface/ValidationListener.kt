package ru.vetano.testgeocoderyandex.webinterface

interface ValidationListener{
    fun showMessage(message: String)
    fun showError(error: String)
    fun showResult(x: String, y: String, address: String)
    fun showUnauthorized(error: String)
}