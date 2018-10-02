package tada.com.tadaproject.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import io.realm.Realm
import tada.com.tadaproject.R
import tada.com.tadaproject.model.User

class RegisterActivity : AppCompatActivity(){

    lateinit var username : EditText
    lateinit var password : EditText
    lateinit var submitButton : Button
    lateinit var checkBox: CheckBox
    lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        Realm.init(this)
        realm = Realm.getDefaultInstance()

        username = findViewById<EditText>(R.id.username_et)
        password = findViewById<EditText>(R.id.password_et)
        submitButton = findViewById<Button>(R.id.button)
        checkBox = findViewById<CheckBox>(R.id.checkBox)


        submitButton.setOnClickListener(View.OnClickListener {
            if(!username.text.isEmpty()){
                if(!password.text.isEmpty()){
                    if(checkBox.isChecked){
                        writeDB(username.text.toString(), password.text.toString())
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }else{
                        checkBox.error = "Term & Condition must be checklist"
                    }
                }else{
                    password.error = "Password can't be empty"
                }
            }else{
                username.error = "Username can't be empty"
            }
        })
    }

    fun writeDB(username : String, password : String){
        realm.executeTransactionAsync({ bgRealm ->
            val draft = bgRealm.createObject(User::class.java!!)
            draft.username = username
            draft.password = password

        }, { }, { error ->
            Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
        })
    }
}