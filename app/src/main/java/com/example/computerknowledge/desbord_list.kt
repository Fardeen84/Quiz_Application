package com.example.computerknowledge

import android.net.Uri

class desbord_list {
     var cout:Int
  lateinit var name:String
    lateinit var gmail:String
    lateinit var pic: String
     var level:Int

    constructor(cout: Int, name: String, gmail: String, pic: String, level: Int) {
        this.cout = cout
        this.name = name
        this.gmail = gmail
        this.pic = pic
        this.level = level
    }

}