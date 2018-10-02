package tada.com.tadaproject.activity

import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import tada.com.tadaproject.R
import tada.com.tadaproject.fragment.FragmentMainView
import tada.com.tadaproject.fragment.ProfilDetail
import tada.com.tadaproject.model.User

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var fragmentMain : FragmentMainView? = null
    lateinit var realm : Realm
    var usernameTextView: TextView?= null
    var emailTextView : TextView?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val navigationView = findViewById<View>(R.id.nav_view) as NavigationView
        val headerView = navigationView.getHeaderView(0)
        usernameTextView = headerView.findViewById(R.id.username_tv) as TextView
        emailTextView = headerView.findViewById(R.id.email_tv) as TextView

        Realm.init(this)
        realm = Realm.getDefaultInstance()


        showData()

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        fragmentMain = Fragment.instantiate(this, FragmentMainView::class.java.getName()) as FragmentMainView
        fragmentManager.beginTransaction().replace(R.id.flLista, fragmentMain).commit()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)){
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.home -> {
                startActivity(Intent(this, SplashScreenActivity::class.java))
            }
            R.id.profile -> {
                Toast.makeText(this, "diklik", Toast.LENGTH_SHORT).show()
                val fragment : Fragment? = ProfilDetail()
                fragmentManager.beginTransaction().replace(R.id.flLista, fragment).commit()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    fun showData() {
        val guests = realm.where(User::class.java!!).findAll()
        for (guest in guests) {
            if (guest.username.isNullOrEmpty()) {
                Log.d("isi username", "gagal")
                Toast.makeText(this, "Gagal", Toast.LENGTH_SHORT).show()
            } else {
                usernameTextView!!.text = guest.username
                emailTextView!!.text = guest.username+"@gmail.com"
            }

        }
    }


}
