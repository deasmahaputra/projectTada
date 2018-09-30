package tada.com.tadaproject.fragment

import android.app.Dialog
import android.app.Fragment
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import tada.com.tadaproject.R
import tada.com.tadaproject.adapter.ListArtAdapter
import tada.com.tadaproject.service.Api

class FragmentMainView : Fragment(){

    private var listAdapter : ListArtAdapter? = null
    lateinit var recyclerView: RecyclerView
    var somethingWrongFragment: SomethingWrongFragment? = null
    lateinit var dialog: Dialog

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        recyclerView = view.findViewById<RecyclerView>(R.id.list_of_art_rv)

        var builder : AlertDialog.Builder = AlertDialog.Builder(activity)
        var inflater : LayoutInflater = layoutInflater
        var v : View = inflater.inflate(R.layout.dialog_loading, null)
        builder.setView(v)
        dialog = builder.create()
        dialog.setCancelable(false)

        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        getData()

        somethingWrongFragment = Fragment.instantiate(activity,
                SomethingWrongFragment::class.java.getName()) as SomethingWrongFragment

        listAdapter = ListArtAdapter(activity)
        recyclerView.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        recyclerView.adapter = listAdapter

        return view
    }

    fun getData() {
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://www.rijksmuseum.nl")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        val apiMatch = retrofit.create(Api::class.java)

        apiMatch.getArt()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    success ->
                    dialog.dismiss()
                    Toast.makeText(activity.applicationContext, success.artObjects[0].title, Toast.LENGTH_SHORT).show()
                    Log.d("test pok", success.artObjects[0].title)
                    listAdapter!!.getData(success.artObjects)

                },
                        {
                            dialog.dismiss()
                            fragmentManager.beginTransaction().replace(R.id.flLista, somethingWrongFragment).commit()
                            Toast.makeText(activity.applicationContext, it.message, Toast.LENGTH_SHORT).show()
                        })
    }

//    fun getData(city : String) {
//        val retrofit: Retrofit = Retrofit.Builder()
//                .baseUrl("https://api.apixu.com")
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build()
//
//        val apiMatch = retrofit.create(Api::class.java)
//    }
}