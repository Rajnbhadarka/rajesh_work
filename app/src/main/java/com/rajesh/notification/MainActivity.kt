package com.rajesh.notification

import androidx.appcompat.app.AppCompatActivity
import com.rajesh.notification.adapter.NotificationAdapter
import androidx.annotation.RequiresApi
import android.os.Build
import android.os.Bundle
import android.content.Intent
import android.view.View
import com.rajesh.notification.model.notificationModel
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity(), MyListener {
    var adapter: NotificationAdapter? = null
    private var mNotifications: ArrayList<notificationModel>? = null
    private lateinit var db: AppDatabase
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS")
        startActivity(intent)
        db = AppDatabase.getDatabase(this)

        MyNotificationListener().setListener(this)
    }

    fun getNotificationdata(){
        mNotifications = ArrayList()
        mNotifications = db.notificationDao().getAll() as ArrayList<notificationModel>

        if (mNotifications!!.size > 0){
            tvNodata.visibility = View.GONE
            rvList.visibility = View.VISIBLE
            adapter = NotificationAdapter(this@MainActivity, mNotifications!!)
            rvList.setAdapter(adapter)
        }else{
            rvList.visibility = View.GONE
            tvNodata.visibility = View.VISIBLE

        }
    }

    override fun onResume() {
        super.onResume()
        getNotificationdata()
    }

    override fun setValue(packageName: String?, title: String?, text: String?) {


            val s = SimpleDateFormat("dd/MM/yyyy hh:mm a")
            val time = s.format(Date())
            val notification = notificationModel()

            notification.packageNameNoti = packageName
            notification.titleNoti = packageName
            notification.textNoti = text
            notification.notiTime = time

            db.notificationDao().insertAll(notification)
            mNotifications = ArrayList()
            mNotifications = db.notificationDao().getAll() as ArrayList<notificationModel>

            if (mNotifications!!.size > 0){
                tvNodata.visibility = View.GONE
                rvList.visibility = View.VISIBLE
                adapter = NotificationAdapter(this@MainActivity, mNotifications!!)
                rvList.setAdapter(adapter)
            }else{
                rvList.visibility = View.GONE
                tvNodata.visibility = View.VISIBLE
            }





    }
}