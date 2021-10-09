package com.rajesh.notification

import android.app.Notification
import android.content.Context
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification


class MyNotificationListener : NotificationListenerService() {
    var context: Context? = null
    var titleData: String? = ""
    var textData = ""
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        if (sbn.notification.flags and Notification.FLAG_GROUP_SUMMARY != 0) {

            //Ignore duplicate notifications which we might receive in apps like whatsapp, gmail, etc...
        } else {
            val pack = sbn.packageName
            val extras = sbn.notification.extras
            titleData = if (extras.getString("android.title") != null) {
                extras.getString("android.title")
            } else {
                "No Title"
            }
            textData = if (extras.getCharSequence("android.text") != null) {
                extras.getCharSequence("android.text").toString()
            } else {
                "No data"
            }
            if (pack != "android") myListener!!.setValue(
                sbn.packageName,
                titleData,
                textData
            )
        }
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification) {}
    override fun onNotificationRankingUpdate(rankingMap: RankingMap) {
        super.onNotificationRankingUpdate(rankingMap)
    }

    fun setListener(myListener: MyListener?) {
        Companion.myListener = myListener
    }

    companion object {
        var myListener: MyListener? = null
    }
}