package com.messieyawo.ispaceroomcoroutineflowproject.data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import coil.load
import com.messieyawo.ispaceroomcoroutineflowproject.R
import com.messieyawo.ispaceroomcoroutineflowproject.data.models.Data



class GenresAdapter(
    private val context: Context,
    private val genres: List<Data>,
    private val itemClickListener: ItemClickListener
) : RecyclerView.Adapter<GenresAdapter.ViewHolder>() {

    interface ItemClickListener {
        fun onItemClick(data: Data)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_all_photos, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = genres.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(genres[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val genre_name = itemView.findViewById<TextView>(R.id.genres_title)
        private val genres_dp = itemView.findViewById<ImageView>(R.id.genres_img)



        fun bind(data: Data) {
           // val contestantName = videosData.title
                genre_name.text = data.name



//            val circularProgressDrawable = CircularProgressDrawable(context)
//            circularProgressDrawable.strokeWidth = 10f
//            circularProgressDrawable.centerRadius = 30f
//            circularProgressDrawable.backgroundColor = context.getColor(R.color.green_400)
//            circularProgressDrawable.start()


         genres_dp.load(data.picture) {
                crossfade(true)
                crossfade(1000)
                placeholder(R.drawable.circularprogressdrawable)

            }


            itemView.setOnClickListener {
                itemClickListener.onItemClick(data)
            }
        }
    }
}
