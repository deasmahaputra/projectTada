package tada.com.tadaproject.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import tada.com.tadaproject.R
import tada.com.tadaproject.model.ItemArtResponse

class ListArtAdapter(private val context: Context) : RecyclerView.Adapter<ListArtAdapter.ListArtViewHolder>(){

    private val listOfArt : MutableList<ItemArtResponse> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListArtViewHolder {
        return ListArtViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list_art, parent, false))
    }

    override fun getItemCount(): Int {
        return listOfArt.size
    }

    override fun onBindViewHolder(holder: ListArtViewHolder, position: Int) {
        return holder.bindModel(listOfArt[position])
    }

    fun getData(data : List<ItemArtResponse>){
        listOfArt.addAll(data)
        notifyDataSetChanged()
    }

    class ListArtViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val textTitleArt : TextView = itemView.findViewById(R.id.list_art_tv)

        fun bindModel(itemofArt : ItemArtResponse){
            textTitleArt.text = itemofArt.title
            Log.d("berhasilkah?", itemofArt.title)
        }
    }
}