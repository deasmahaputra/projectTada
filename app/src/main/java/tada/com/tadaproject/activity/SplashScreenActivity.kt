package tada.com.tadaproject.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import io.realm.Realm
import tada.com.tadaproject.R
import tada.com.tadaproject.model.User

class SplashScreenActivity : AppCompatActivity(){

    private val splash_screen = 1000
    lateinit var realm : Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Realm.init(this)
        realm = Realm.getDefaultInstance()

        Handler().postDelayed({
            login()
            //startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, splash_screen.toLong())
    }

    fun login(){
        val guests = realm.where(User::class.java!!).findAll()
            if (guests != null && guests!!.size > 0) {
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                startActivity(Intent(this, LoginActivity::class.java))

        }
    }
}