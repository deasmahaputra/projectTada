package tada.com.tadaproject.fragment

import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import tada.com.tadaproject.R
import tada.com.tadaproject.activity.MainActivity


class SomethingWrongFragment : Fragment(){

    lateinit var button: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_something_wrong, container, false)

        button = view.findViewById(R.id.button)

        button.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)

        })

        return view
    }

}