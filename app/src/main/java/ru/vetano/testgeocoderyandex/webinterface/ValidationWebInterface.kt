package ru.vetano.testgeocoderyandex.webinterface

import android.webkit.JavascriptInterface

class ValidationWebInterface(private val validationListener: ValidationListener) {

    @JavascriptInterface
    fun showMessage(message: String) {
        validationListener.showMessage(message)
    }

    @JavascriptInterface
    fun showError(error: String) {
        validationListener.showError(error)
    }

    @JavascriptInterface
    fun showResult(x: String, y: String, address: String) {
        validationListener.showResult(x, y, address)
    }

    @JavascriptInterface
    fun showUnauthorized(error: String){
        validationListener.showUnauthorized(error)
    }
}