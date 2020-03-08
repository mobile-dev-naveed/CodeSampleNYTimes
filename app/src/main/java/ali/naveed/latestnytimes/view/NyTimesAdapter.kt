package ali.naveed.latestnytimes.view

import ali.naveed.latestnytimes.R
import ali.naveed.latestnytimes.model.ItemResult
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
class NyTimesAdapter(private val myDataset: MutableList<ItemResult>,val itemClickListener: ItemClickListener?) :
    RecyclerView.Adapter<NyTimesAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val titleTextView: TextView = view.itemTitleTv
        val desTextView: TextView = view.itemDesTv
        val dateTextView: TextView = view.itemDateTv
        val imv:ImageView = view.itemImv

        init {
            view.setOnClickListener { itemClickListener?.onClicked(adapterPosition) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): NyTimesAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.new_list_item, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.titleTextView.text = myDataset[position].title?:""
        holder.desTextView.text = myDataset[position].byline?:"--"
        holder.dateTextView.text = myDataset[position].publishedDate?:"--"

        val url:String? = myDataset[position].media?.firstOrNull()?.mediaMetadata?.firstOrNull()?.url
        Glide.with(holder.imv)
            .load(url?:R.drawable.cirle)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.imv)

    }

    override fun getItemCount() = myDataset.size
}

interface ItemClickListener{
    fun onClicked(position:Int)
}