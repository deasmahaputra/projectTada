package tada.com.tadaproject.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import tada.com.tadaproject.R

class LoginActivity : AppCompatActivity(){

    lateinit var username : EditText
    lateinit var password : EditText
    lateinit var registerButton : TextView
    lateinit var submitButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        username = findViewById<EditText>(R.id.username_et)
        password = findViewById<EditText>(R.id.password_et)
        registerButton = findViewById<TextView>(R.id.register_btn)
        submitButton = findViewById<Button>(R.id.button)

        submitButton.setOnClickListener(View.OnClickListener {
            if(!username.text.isEmpty() && !password.text.isEmpty()){
                startActivity(Intent(this, MainActivity::class.java))
            }else{
                username.error = "Username can't be empty"
                password.error = "Password can't be empty"
            }

        })

        registerButton.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        })
    }
}