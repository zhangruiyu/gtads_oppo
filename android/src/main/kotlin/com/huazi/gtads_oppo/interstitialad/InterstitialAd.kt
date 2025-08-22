package com.huazi.gtads_oppo.interstitialad

import android.annotation.SuppressLint
import android.app.Activity
import com.huazi.gtads_oppo.FlutterHuaweiAdEventPlugin
import com.heytap.msp.mobad.api.ad.InterstitialAd
import com.heytap.msp.mobad.api.listener.IInterstitialAdListener
import com.huazi.gtads_oppo.LogUtil

@SuppressLint("StaticFieldLeak")
object InterstitialAd {
    private val TAG = "InterstitialAd"

    private lateinit var context: Activity
    private var vivoInterstitialAd: InterstitialAd? = null

    private var codeId: String? = null


    fun init(context: Activity, androidId: String) {
        this.context = context
        this.codeId = androidId
        loadInterstitialAD()
    }

    private fun loadInterstitialAD() {
        vivoInterstitialAd =
            InterstitialAd(context, codeId)
        vivoInterstitialAd?.setAdListener(interstitialAdListener)
        vivoInterstitialAd?.loadAd()
    }

    fun showAd() {
        if (vivoInterstitialAd == null) {
            var map: MutableMap<String, Any?> =
                mutableMapOf("adType" to "interactAd", "onAdMethod" to "onUnReady")
            FlutterHuaweiAdEventPlugin.sendContent(map)
            LogUtil.e("$TAG  插屏全屏视频广告显示失败，无广告")
            return
        }
        vivoInterstitialAd?.showAd()
    }

    private var interstitialAdListener = object : IInterstitialAdListener {
        override fun onAdReady() {
            LogUtil.e("$TAG  插屏全屏视频视频广告，渲染成功")
            var map: MutableMap<String, Any?> =
                mutableMapOf("adType" to "interactAd", "onAdMethod" to "onReady")
            FlutterHuaweiAdEventPlugin.sendContent(map)
        }


        override fun onAdFailed(code: Int, error: String?) {
            LogUtil.e("$TAG  插屏全屏视频视频广告，渲染失败 errorCode: ${error} ${code}")
            var map: MutableMap<String, Any?> = mutableMapOf(
                "adType" to "interactAd",
                "onAdMethod" to "onFail",
                "code" to code,
                "message" to error
            )
            FlutterHuaweiAdEventPlugin.sendContent(map)
            vivoInterstitialAd = null
        }


        override fun onAdFailed(p0: String?) {
            ///废弃了
        }


        override fun onAdClick() {
            LogUtil.e("$TAG  插屏全屏视频广告点击时回调")
            var map: MutableMap<String, Any?> =
                mutableMapOf("adType" to "interactAd", "onAdMethod" to "onClick")
            FlutterHuaweiAdEventPlugin.sendContent(map)
        }

        override fun onAdShow() {
            LogUtil.e("$TAG  插屏全屏视频广告展开时回调")
            var map: MutableMap<String, Any?> =
                mutableMapOf("adType" to "interactAd", "onAdMethod" to "onShow")
            FlutterHuaweiAdEventPlugin.sendContent(map)
        }

        override fun onAdClose() {
            LogUtil.e("$TAG  插屏全屏视频广告关闭时回调")
            var map: MutableMap<String, Any?> =
                mutableMapOf("adType" to "interactAd", "onAdMethod" to "onClose")
            FlutterHuaweiAdEventPlugin.sendContent(map)
            vivoInterstitialAd = null
        }
    }
}
