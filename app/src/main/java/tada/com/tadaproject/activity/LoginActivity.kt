package tada.com.tadaproject.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import io.realm.Realm
import tada.com.tadaproject.R
import tada.com.tadaproject.model.User

class LoginActivity : AppCompatActivity(){

    lateinit var username : EditText
    lateinit var password : EditText
    lateinit var registerButton : TextView
    lateinit var submitButton : Button
    lateinit var realm : Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Realm.init(this)
        realm = Realm.getDefaultInstance()

        username = findViewById<EditText>(R.id.username_et)
        password = findViewById<EditText>(R.id.password_et)
        registerButton = findViewById<TextView>(R.id.register_btn)
        submitButton = findViewById<Button>(R.id.button)

        submitButton.setOnClickListener(View.OnClickListener {
            if(!username.text.isEmpty()){
                if(!password.text.isEmpty()){
                    startActivity(Intent(this, MainActivity::class.java))
                    writeDB(username.text.toString(), password.text.toString())
                }else{
                    password.error = "Password can't be empty"
                }
            }else{
                username.error = "Username can't be empty"
            }

        })

        registerButton.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        })
    }

    fun writeDB(username : String, password : String){
//        realm.executeTransactionAsync(object : Realm.)

        realm.executeTransactionAsync({ bgRealm ->
            val draft = bgRealm.createObject(User::class.java!!)
            draft.username = username
            draft.password = password

        }, { }, { error ->
            Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
            // Transaction failed and was automatically canceled.
        })
    }
}