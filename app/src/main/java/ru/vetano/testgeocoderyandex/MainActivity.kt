package ru.vetano.testgeocoderyandex

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import ru.vetano.testgeocoderyandex.custom.AddressValidationWebView
import ru.vetano.testgeocoderyandex.webinterface.ValidationListener
import ru.vetano.testgeocoderyandex.webinterface.ValidationWebInterface

class MainActivity : AppCompatActivity(), ValidationListener {

    private lateinit var validationView: AddressValidationWebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        check.setOnClickListener { checkAddress() }

        validationView = AddressValidationWebView(this)
        validationView.setJavaScriptInterfaceListener(ValidationWebInterface(this))
    }

    private fun checkAddress(){
        validationView.checkValidationAddress(address.text.toString())
    }

    fun Context.showToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showMessage(message: String) {
        showToast(message)
    }

    override fun showError(error: String) {
        showToast(error)
    }

    override fun showResult(x: String, y: String, address: String) {
        showToast("$x $y $address")
    }

    override fun showUnauthorized(error: String) {
        showToast(error)
    }
}
