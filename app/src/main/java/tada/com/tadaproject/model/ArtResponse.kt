package tada.com.tadaproject.model

import com.google.gson.annotations.SerializedName

class ArtResponse {

        @SerializedName("artObjects")
        var art: MutableList<ItemArtResponse> = mutableListOf()

}