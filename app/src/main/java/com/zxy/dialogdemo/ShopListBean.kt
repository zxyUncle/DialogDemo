package com.zxy.dialogdemo

import android.annotation.SuppressLint
import android.os.Parcelable

import kotlinx.android.parcel.Parcelize


/**
 * Created by zxy on 2020/9/28 14:40
 * ******************************************
 * * 商城-商品列表
 * ******************************************
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class ShopListBean(
        var message: String? = "",
        var code: String? = null, // 0000
        var goodsList: MutableList<Goods?>? = null,
        var success: Boolean? = null, // true
        var totalNum: Int? = null // 25
) : Parcelable {
    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Goods(
            var cataIdList: MutableList<String?>? = null,
            var goodsId: String? = null, // 4260290260270
            var goodsName: String? = null, // 瓦伦丁窖藏啤酒
            var inventory: Int? = null, // 67
            var lockStock: Int? = null, // 17
            var onSellGoodsId: String? = null, // 2007170042
            var price: String? = null, // 0.50
            var safeStock: Int? = null, // 0
            var saleCount: Int? = null, // 18
            var specification: String? = null, // 200ml
            var status: String? = null, // ON_SELL
            var storeId: String? = null, // SHP001
            var timestamp: Long? = null, // 1600150149201
            var version: Int? = null, // 82
            var selectGoodsNum: Int = 0 //选择的商品数量
    ) : Parcelable
}