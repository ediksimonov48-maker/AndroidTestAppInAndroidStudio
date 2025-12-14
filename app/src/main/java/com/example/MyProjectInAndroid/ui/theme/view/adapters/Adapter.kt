package com.example.MyProjectInAndroid.ui.theme.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.MyProjectInAndroid.R
import com.example.MyProjectInAndroid.ui.theme.data.ResponseInDB

class Adapter(private val filmlist: ArrayList<ResponseInDB>,
              val mItemClickListener: ItemClickListener) : RecyclerView.Adapter<Adapter.MyViewHolder>() {

    interface ItemClickListener{
        fun onItemClick(id: String)
    }
    // создает новый объект ViewHolder для каждого элемента в RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // Inflate the layout for each item and return a new ViewHolder object
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.text_row_item, parent, false)
        return MyViewHolder(itemView)
    }

    // This method returns the total
    // number of items in the data set
    override fun getItemCount(): Int {
        return filmlist.size
    }
    // новый метод
    fun addItems(newItems: ArrayList<ResponseInDB>) {
        filmlist.clear()
        filmlist.addAll(newItems)
        notifyDataSetChanged()
    }

    // This method binds the data to the ViewHolder object
    // for each item in the RecyclerView
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentEmp = filmlist[position]
        holder.name.text = currentEmp.Title
        Glide.with(holder.itemView.context)
            .load(currentEmp.Poster)
            .placeholder(android.R.color.transparent) // Можно выбрать подходящий placeholder
            .into(holder.image)
    }

    // This class defines the ViewHolder object for each item in the RecyclerView
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tvName)
        val image: ImageView = itemView.findViewById(R.id.imageView)

        init {
            itemView.setOnClickListener {
                filmlist?.get(position)?.imdbID?.let{it -> mItemClickListener.onItemClick(it)}
            }
        }
    }
}