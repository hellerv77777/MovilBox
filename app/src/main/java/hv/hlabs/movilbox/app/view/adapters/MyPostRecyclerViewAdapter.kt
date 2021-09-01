package hv.hlabs.movilbox.app.view.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import hv.hlabs.movilbox.R
import hv.hlabs.movilbox.app.database.entities.PostEntity
import hv.hlabs.movilbox.app.view.PostListener

class MyPostRecyclerViewAdapter(private var mlistener: PostListener) : RecyclerView.Adapter<MyPostRecyclerViewAdapter.ViewHolder>() {

    private var values:List<PostEntity> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_item_post, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = values[position]
        holder.textViewTittle.text = values[position].title
        holder.textViewBody.text = values[position].body

        if(values[position].favorite==1){
            holder.imageButtonFavorie.setBackgroundResource(R.drawable.ic_baseline_star_24)
        }else{
            holder.imageButtonFavorie.setBackgroundResource(R.drawable.ic_baseline_star_border_24)
        }

        if(values[position].id<=20 && values[position].leido==0){
            holder.linear.visibility = View.VISIBLE
        }else{
            holder.linear.visibility = View.GONE
        }

        holder.card.setOnClickListener {
            mlistener.onClickPost(item)
        }

        holder.imageButtonFavorie.setOnClickListener {
            mlistener.onClickFavorite(item)
        }
    }

    override fun getItemCount(): Int = values.size

    fun updateItems(values: List<PostEntity>) {
        this.values = values
        notifyDataSetChanged()
    }

    fun getPost(pos: Int): PostEntity {
        return values[pos]
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewTittle: TextView = view.findViewById(R.id.textViewTittle)
        val textViewBody:TextView = view.findViewById(R.id.textViewBody)
        val imageButtonFavorie:ImageView = view.findViewById(R.id.imageButtonFavorie)
        val card: CardView = view.findViewById(R.id.card)
        val linear: LinearLayout = view.findViewById(R.id.linear)
    }
}

