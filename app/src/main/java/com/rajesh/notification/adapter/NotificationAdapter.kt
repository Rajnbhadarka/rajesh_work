package com.rajesh.notification.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rajesh.notification.R
import com.rajesh.notification.adapter.NotificationAdapter.ImageViewholder
import com.rajesh.notification.model.notificationModel

class NotificationAdapter(
    private val mContext: Context,
    private val mNotifications: ArrayList<notificationModel>
) : RecyclerView.Adapter<ImageViewholder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewholder {
        val v = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false)
        return ImageViewholder(v)
    }

    override fun onBindViewHolder(holder: ImageViewholder, position: Int) {
        val data = mNotifications[position]

        if (data.titleNoti!!.contains("whatsapp",true)){
            holder.title.text = "Application Name : " +"Whatsapp"
        }else if(data.titleNoti!!.contains("com.google.android.gm",true)){
            holder.title.text = "Application Name : " +"Gmail"
        }else if(data.titleNoti!!.contains("facebook",true)){
            holder.title.text = "Application Name : " +"Facebook"
        }else if(data.titleNoti!!.contains("flipkart",true)){
            holder.title.text = "Application Name : " +"Flipkart"
        }else{
            holder.title.text = "Application Name : " +data.titleNoti
        }


        holder.message.text = "Message : " +data.textNoti
        holder.time.text = "Time : " +data.notiTime

    }

    override fun getItemCount(): Int {
        return mNotifications.size
    }

    inner class ImageViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var message: TextView
        var time: TextView

        init {
            title = itemView.findViewById(R.id.tvName)
            message = itemView.findViewById(R.id.tvText)
            time = itemView.findViewById(R.id.tvTime)

        }
    }
}