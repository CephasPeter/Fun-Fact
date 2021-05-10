package com.code.fun_fact.ui.login

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.code.fun_fact.DetailActivity
import com.code.fun_fact.FactAdapter
import com.code.fun_fact.MainActivity

import com.code.fun_fact.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val login = findViewById<Button>(R.id.login)

        val email = "cephasarowolo@gmail.com"
        val passwordText = "11111111"

        username.afterTextChanged {
            if (username.text.toString().isBlank()){
                username.error = "Email Cant Be Empty"
                login.isEnabled = false
            }else{
                if (!(password.text.toString().isBlank())){
                    login.isEnabled = true
                }
            }
        }

        password.afterTextChanged {
            if (password.text.toString().isBlank()){
                password.error = "Password Cant Be Blank"
                login.isEnabled = false
            }else{
                if (!(username.text.toString().isBlank())){
                    login.isEnabled = true
                }
            }
        }

        password.apply {
            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        if (username.text.toString() == email && password.text.toString() == passwordText){
                            updateUiWithUser(email)
                        }else{
                            showLoginFailed("Credientials Invalid")
                        }
                }
                false
            }

            login.setOnClickListener {
                if (username.text.toString() == email && password.text.toString() == passwordText){
                    updateUiWithUser(email)
                }else{
                    showLoginFailed("Credientials Invalid")
                }
            }
        }
    }

    private fun updateUiWithUser(username:String) {
        val welcome = getString(R.string.welcome)

        Toast.makeText(applicationContext, "$welcome $username", Toast.LENGTH_LONG).show()

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun showLoginFailed(errorString: String) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}