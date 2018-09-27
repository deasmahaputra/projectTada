package tada.com.tadaproject.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import tada.com.tadaproject.R

class SplashScreenActivity : AppCompatActivity(){

    private val splash_screen = 1000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, splash_screen.toLong())


    }
}