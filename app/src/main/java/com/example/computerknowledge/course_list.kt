package com.example.computerknowledge

import android.net.Uri

class course_list {
    var name:String?=null
    var dis:String?=null
    var pic:Uri?=null
    var key:String?=null

    constructor(name:String,dis:String,pic: Uri,key:String)
    {
        this.name=name;
        this.dis=dis
        this.pic=pic
        this.key=key
    }

//    fun getName():String? {
//        return name
//    }
//    fun getDis():String?{
//        return dis
//    }
//    fun getPic():Int?{
//        return pic
//    }

}