package tada.com.tadaproject.service

import io.reactivex.Observable
import retrofit2.http.GET
import tada.com.tadaproject.model.ArtResponse

interface Api {

    @GET("/api/nl/collection?key=DhC4gl3s&format=json&type=schilderij&f.normalized32Colors.hex=%20%23367614")
    fun getArt() : Observable<ArtResponse>



}