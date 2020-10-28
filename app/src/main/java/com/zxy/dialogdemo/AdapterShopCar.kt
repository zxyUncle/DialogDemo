package com.zxy.dialogdemo

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * Created by zxy on 2020/9/27 11:32
 * ******************************************
 * * 商城购物车商品列表适配器
 * ******************************************
 */
class AdapterShopCar : BaseQuickAdapter<String, BaseViewHolder>(R.layout.adapter_shop) {

    override fun convert(helper: BaseViewHolder, item: String) {
    }


}