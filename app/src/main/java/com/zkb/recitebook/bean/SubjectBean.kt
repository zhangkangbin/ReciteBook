package com.zkb.recitebook.bean

import java.util.*

class SubjectBean :BaseBean() {

    var subject:String?=null;
    /*
    fill the blank spaces with right words?
     */
    var subjectType:Int=0;
    var answer:String?="answer?";
    var tips:String?="tips??";
    var time:String?= Date().toString();
    /*
    type:0 normal,1 forget, 2 remember
     */
    var memoryType:Int=0;
    var forget:Int=0;
    /*
    1 是收藏， 0 不是
     */
    var collect:Int=0;

    /**
     *
     */
    var remember:Int=0;
    /*
    the is book id
     */
    var bookId:String?=null;
    var id:Int=0;
    override fun toString(): String {
        return super.toString()
    }
}