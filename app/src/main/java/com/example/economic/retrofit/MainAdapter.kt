package com.example.economic.retrofit

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.economic.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main_news.view.*
import kotlinx.android.synthetic.main.fragment_home.*

class MainAdapter(val results : ArrayList<MainModel.Result>, val listener : OnAdapterListener):
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.activity_main_news, parent,false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var result = results[position]
        holder.view.text_view_recycle.text = result.title
        Picasso.get().load(result.image).fit()
            .centerCrop()
            .error(R.drawable.ic_baseline_bathtub_24)
            .placeholder(R.drawable.ic_baseline_person_24)
            .into(holder.view.image_recycle)
        holder.view.setOnClickListener({
            listener.onClick(result)
        })
    }

    fun setData(data: List<MainModel.Result>){
        results.clear()
        results.addAll(data)
        notifyDataSetChanged()
    }
    override fun getItemCount() = results.size

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view)

    interface OnAdapterListener{
        fun onClick(result: MainModel.Result)
    }
}