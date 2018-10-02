package tada.com.tadaproject.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

/**
 * Created by Deas on 10/2/18.
 */
open class User : RealmObject(){

    @SerializedName("username")
    var username: String? = null
    @SerializedName("password")
    var password: String? = null
}