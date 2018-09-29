package tada.com.tadaproject.fragment

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import tada.com.tadaproject.R
import tada.com.tadaproject.adapter.ListArtAdapter
import tada.com.tadaproject.service.Api

class FragmentMainView : Fragment(){

    private lateinit var listAdapter : ListArtAdapter
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        recyclerView = view.findViewById<RecyclerView>(R.id.list_of_art_rv)

        getData()

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
                    Log.d("test pok", success.art[0].title)
                    listAdapter.getData(success.art)
                },
                        {
                            //                            fragmentManager.beginTransaction().replace(R.id.flLista, errorFragment).commit()
//                            Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
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