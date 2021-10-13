package com.zxy.zxydialog.tools

import java.text.DecimalFormat

/**
 * Created by zsf on 2021/10/13 16:27
 * ******************************************
 * * 数值工具类
 * ******************************************
 */
object DecimalTools {
    /**
     * 格式化数值
     * @param format 格式：
     *  "#" 所有整数
     *  "#.0" 所有整数，1位小数
     *  "00.000" 2位整数，3位小数
     *  ",###" 每三位用,隔开，只对整数有效
     */
    fun format(value: Any, format: String): String {
        return DecimalFormat(format).format(value)
    }
}