package tada.com.tadaproject.fragment

import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import io.realm.Realm
import tada.com.tadaproject.R
import tada.com.tadaproject.activity.SplashScreenActivity
import tada.com.tadaproject.model.User



/**
 * Created by Deas on 10/2/18.
 */
class ProfilDetail : Fragment(){

    lateinit var realm : Realm
    lateinit var usernameTextView: TextView
    lateinit var logoutButton : Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_profil_detail, container, false)

        usernameTextView = view.findViewById(R.id.username_detail_et)
        logoutButton = view.findViewById(R.id.logout_btn)


        Realm.init(activity)
        realm = Realm.getDefaultInstance()

        showData()

        logoutButton.setOnClickListener({
            realm.executeTransaction { realm ->
                realm.where(User::class.java).findAll().deleteAllFromRealm()
            }
            startActivity(Intent(activity, SplashScreenActivity::class.java))
        })

        return view
    }

    fun showData() {
        val guests = realm.where(User::class.java!!).findAll()
        for (guest in guests) {
            if (guest.username.isNullOrEmpty()) {
                Log.d("isi username", "gagal")
            } else {
                usernameTextView!!.text = guest.username
            }

        }
    }
}