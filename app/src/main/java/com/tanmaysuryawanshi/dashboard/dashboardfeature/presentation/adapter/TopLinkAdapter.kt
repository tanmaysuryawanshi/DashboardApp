package com.tanmaysuryawanshi.dashboard.dashboardfeature.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tanmaysuryawanshi.dashboard.R
import com.tanmaysuryawanshi.dashboard.dashboardfeature.domain.model.TopLink
import com.tanmaysuryawanshi.dashboard.dashboardfeature.utils.convertDateFormat

class TopLinkAdapter(private val list:List<TopLink>) :RecyclerView.Adapter<TopLinkAdapter.LinkViewHolder>() {

    private lateinit var mListener:LinkClickedInterface
    interface LinkClickedInterface{
fun linkClicked(position: Int)
fun copyClicked(position: Int)
    }

    fun setOnInterfaceClickListner(listener:LinkClickedInterface){
        mListener=listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinkViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item,parent,false)
        return LinkViewHolder(itemView,mListener)
    }

    override fun getItemCount(): Int {
      return list.size
    }

    override fun onBindViewHolder(holder: LinkViewHolder, position: Int) {
      val currentItem=list[position]
holder.linkNameTV.text=currentItem.title
        holder.linkDateRvitemTV.text= convertDateFormat( currentItem.created_at)
        holder.linkAddressTV.text=currentItem.smart_link
        holder.clicksCountRvitemTV.text=currentItem.total_clicks.toString()
        Glide.with(holder.itemView).load(currentItem.original_image).into(holder.imageView)
    }

    class LinkViewHolder(itemView: View,listner:LinkClickedInterface):RecyclerView.ViewHolder(itemView)
    {
val imageView:ImageView=itemView.findViewById(R.id.imageView)

        val linkNameTV:TextView=itemView.findViewById(R.id.linkNameRvitemTV)
        val linkDateRvitemTV:TextView=itemView.findViewById(R.id.linkDateRvitem)
        val clicksCountRvitemTV:TextView=itemView.findViewById(R.id.clicksCountRvitemTV)
        val linkAddressTV:TextView=itemView.findViewById(R.id.linkAddressTV)
val copyImage:ImageView=itemView.findViewById(R.id.copyImageView)
init {
    copyImage.setOnClickListener{
        listner.copyClicked(adapterPosition)
    }
    linkAddressTV.setOnClickListener {
        listner.linkClicked(adapterPosition)
    }

}

    }
}


