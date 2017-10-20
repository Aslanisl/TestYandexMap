package ru.vetano.testgeocoderyandex.custom

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import ru.vetano.testgeocoderyandex.webinterface.ValidationWebInterface

internal class AddressValidationWebView : WebView {

    constructor(context: Context) : super(context) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView(context)
    }

    private fun initView(context: Context) {
        settings.javaScriptEnabled = true
        webChromeClient = WebChromeClient()

        try {
            val inputStream = context.assets.open("input_validation.html")
            val buffer = ByteArray(inputStream.available())
            if (inputStream.read(buffer) <= 0) Log.d("TAG", "Can't read embedded assets index.html file!")
            inputStream.close()

            val htmlText = String(buffer)
            loadDataWithBaseURL(
                    "http://ru.vetano.testgeocoderyandex.ymapapp",
                    htmlText,
                    "text/html",
                    "UTF-8",
                    null
            )
        } catch (e: Exception) {
            Log.d("TAG", e.toString())
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setWebContentsDebuggingEnabled(true)
        }
    }

    fun setJavaScriptInterfaceListener(validationWebInterface: ValidationWebInterface) {
        addJavascriptInterface(validationWebInterface, "Android")
    }

    fun checkValidationAddress(address: String) {
        val jsFunction = "javascript:geocode('$address')"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            evaluateJavascript(jsFunction) { value -> Log.d("webView", value) }
        } else {
            loadUrl(jsFunction)
        }
    }
}