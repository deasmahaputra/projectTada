package tada.com.tadaproject.fragment

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import tada.com.tadaproject.R
import tada.com.tadaproject.service.Api

class FragmentMainView : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        return view
    }

    fun getData(city : String) {
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://api.apixu.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        val apiMatch = retrofit.create(Api::class.java)
    }
}