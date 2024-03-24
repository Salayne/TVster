package com.example.tvster

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class TvAdapter(private val shows: List<Show>, private val mListener: OnListFragmentInteractionListener?) : RecyclerView.Adapter<TvAdapter.TvViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvAdapter.TvViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.popular_tv_show, parent, false)
        return TvViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvAdapter.TvViewHolder, position: Int) {
        val show = shows[position]


        holder.mitem = show
        holder.mtitle.text = show.title
        /*holder.mOverview.text = show.overview*/
        holder.mAirDate.text = show.airDate

        Glide.with(holder.mView)
            .load("https://image.tmdb.org/t/p/w500/" + show.posterUrl)
            .centerInside()
            .into(holder.mposter)

        holder.mView.setOnClickListener {
            holder.mitem?.let { show ->
                mListener?.onItemClick(show)
            }
        }
    }

    override fun getItemCount(): Int {
        return shows.size
    }

    inner class TvViewHolder(val mView: View) : RecyclerView.ViewHolder(mView){
        var mitem: Show? = null
        val mtitle: TextView = mView.findViewById<View>(R.id.mediaTitle) as TextView
        /*val mOverview: TextView = mView.findViewById<View>(R.id.mediaOverview) as TextView*/
        val mAirDate: TextView = mView.findViewById<View>(R.id.mediaAirDate) as TextView
        var mposter: ImageView = mView.findViewById<View>(R.id.mediaImage) as ImageView


        override fun toString(): String {
            return mtitle.toString()// + " '" + mOverview.text
        }


    }
}