package tada.com.tadaproject.fragment

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.realm.Realm
import tada.com.tadaproject.R

/**
 * Created by Deas on 10/2/18.
 */
class FragmentDetailArt : Fragment(){

    lateinit var nameTextView: TextView
    var titleArt = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_detail_art, container, false)

        nameTextView = view.findViewById(R.id.name_art_tv)

        val bundle = arguments
        titleArt = bundle.getString(FragmentMainView.onClickArtName)

        nameTextView.text = titleArt

        return view
    }

}