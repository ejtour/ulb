package com.ulb.code.config

import com.ulb.common.base.BaseConfig

class UrlConfig : BaseConfig() {

    companion object {
        //登录相关
        var loginHost = "http://dohko.login.hualala.com/"

        //wms业务相关域名
        var wmsHost = "http://dohko.wms.supplychain.hualala.com/"
    }
}