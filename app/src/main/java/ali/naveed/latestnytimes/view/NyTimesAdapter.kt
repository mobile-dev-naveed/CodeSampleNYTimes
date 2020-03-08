package ali.naveed.latestnytimes.view

import ali.naveed.latestnytimes.R
import ali.naveed.latestnytimes.model.ItemResult
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.new_list_item.view.*




/**
 * @author Naveed Ali
 * @CreatedOn 05/03/2020
 */
class NyTimesAdapter(val itemClickListener: ItemClickListener?) :
    RecyclerView.Adapter<NyTimesAdapter.MyViewHolder>(),Filterable {
    var filterList: MutableList<ItemResult> = ArrayList()
    private var dataset: MutableList<ItemResult> = ArrayList()
    init {
        filterList.addAll(dataset)
    }
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val titleTextView: TextView = view.itemTitleTv
        val desTextView: TextView = view.itemDesTv
        val dateTextView: TextView = view.itemDateTv
        val imv:ImageView = view.itemImv

        init {
            view.setOnClickListener { itemClickListener?.onClicked(adapterPosition,filterList[adapterPosition]) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): NyTimesAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.new_list_item, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.titleTextView.text = filterList[position].title?:""
        holder.desTextView.text = filterList[position].byline?:"--"
        holder.dateTextView.text = filterList[position].publishedDate?:"--"

        val url:String? = filterList[position].media?.firstOrNull()?.mediaMetadata?.firstOrNull()?.url
        Glide.with(holder.imv)
            .load(url?:R.drawable.cirle)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.imv)

    }

    override fun getItemCount() = filterList.size

    fun setData(data: MutableList<ItemResult>){
        dataset.clear()
        dataset.addAll(data)
        filterList = data
        notifyDataSetChanged()
    }
    fun onQueryChanged(newText: String?) {
        newText?.let {
            filter.filter(newText)
        }
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(charSequence : CharSequence?): FilterResults {
                val charString = charSequence.toString()
                filterList = if (charString.isEmpty()) {
                    dataset
                } else {
                    val filteredListTemp:MutableList<ItemResult> = ArrayList()
                    dataset.forEach {
                        if (it.title?.toLowerCase()?.contains(charString.toLowerCase()) == true) {
                            filteredListTemp.add(it)
                        }
                    }
                    filteredListTemp
                }

                val filterResults = FilterResults()
                filterResults.values = filterList
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence?, filterResults: FilterResults?) {
                filterList = filterResults?.values as MutableList<ItemResult>
                notifyDataSetChanged()
            }

        }
    }
}

interface ItemClickListener{
    fun onClicked(position:Int,item:ItemResult)
}