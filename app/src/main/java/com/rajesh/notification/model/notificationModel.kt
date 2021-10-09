package com.rajesh.notification.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
class notificationModel:Serializable {

    @SerializedName("id")
    @Expose
    @PrimaryKey
     var id: Int? = null

    @SerializedName("packageNameNoti")
    @Expose
    @ColumnInfo(name = "packageNameNoti")
    var packageNameNoti: String? = null

    @SerializedName("titleNoti")
    @Expose
    @ColumnInfo(name = "titleNoti")
    var titleNoti: String? = null

    @SerializedName("textNoti")
    @Expose
    @ColumnInfo(name = "textNoti")
    var textNoti: String? = null

    @SerializedName("notiTime")
    @Expose
    @ColumnInfo(name = "notiTime")
    var notiTime: String? = null

}