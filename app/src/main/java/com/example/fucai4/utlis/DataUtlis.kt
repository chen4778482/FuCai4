package com.example.fucai4.utlis

import java.text.SimpleDateFormat
import java.util.*



/**
 * Created by 42224 on 2018/4/18.
 */
class DataUtlis {
    companion object {
        fun DangData(): String {
            val simpleDateFormat = SimpleDateFormat("yyyyMMddHHmmss")// HH:mm:ss
            //获取当前时间
            val date = Date(System.currentTimeMillis())
            return simpleDateFormat.format(date)
        }

        fun getDateStr(day: String): String {
            val nowTime = Date(day)
            val time = SimpleDateFormat("yyyyMMddHHmmss")
            return  time.format(nowTime).toString()
        }

        /**
         *
         * 日期加减。
         * @param base 基础日期
         * @param days 增加天数(减天数则用负数)
         * @return 计算结果
         */
        fun datePlus( days: Int,data:String): String {
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")//
            val date = Date(System.currentTimeMillis())
            val cal = Calendar.getInstance()
            cal.time = date
            cal.add(Calendar.DATE, days)
            return simpleDateFormat.format(cal.time)
        }

        fun compare_date(DATE1: String, DATE2: String): Int {
            val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            try {
                val dt1 = df.parse(DATE1)
                val dt2 = df.parse(DATE2)
                if (dt1.time > dt2.time) {
                    println("dt1 在dt2前")
                    return 1
                } else if (dt1.time < dt2.time) {
                    println("dt1在dt2后")
                    return -1
                } else {
                    return 0
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
            }

            return 0
        }

        /**
         * 截取字符串str中指定字符 strStart、strEnd之间的字符串
         *
         * @param string
         * @param str1
         * @param str2
         * @return
         */
        fun subString(str: String, strStart: String, strEnd: String): String {

            /* 找出指定的2个字符在 该字符串里面的 位置 */
            val strStartIndex = str.indexOf(strStart)
            val strEndIndex = str.indexOf(strEnd)

            /* index 为负数 即表示该字符串中 没有该字符 */
            if (strStartIndex < 0) {
                return "字符串 :---->$str<---- 中不存在 $strStart, 无法截取目标字符串"
            }
            if (strEndIndex < 0) {
                return "字符串 :---->$str<---- 中不存在 $strEnd, 无法截取目标字符串"
            }
            /* 开始截取 */
            val result = str.substring(strStartIndex, strEndIndex).substring(strStart.length)
            return result
        }
    }



}