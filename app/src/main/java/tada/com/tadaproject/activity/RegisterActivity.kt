package tada.com.tadaproject.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import tada.com.tadaproject.R

class RegisterActivity : AppCompatActivity(){

    lateinit var username : EditText
    lateinit var password : EditText
    lateinit var submitButton : Button
    lateinit var checkBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        username = findViewById<EditText>(R.id.username_et)
        password = findViewById<EditText>(R.id.password_et)
        submitButton = findViewById<Button>(R.id.button)
        checkBox = findViewById<CheckBox>(R.id.checkBox)


        submitButton.setOnClickListener(View.OnClickListener {
            if(!username.text.isEmpty()){
                if(!password.text.isEmpty()){
                    if(checkBox.isChecked){
                        startActivity(Intent(this, MainActivity::class.java))
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
}