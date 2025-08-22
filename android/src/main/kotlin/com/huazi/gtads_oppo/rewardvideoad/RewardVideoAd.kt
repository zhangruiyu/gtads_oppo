package com.huazi.gtads_oppo.rewardvideoad

import android.annotation.SuppressLint
import android.app.Activity
import android.content.DialogInterface
import com.heytap.msp.mobad.api.ad.RewardVideoAd
import com.heytap.msp.mobad.api.listener.IRewardVideoAdListener
import com.huazi.gtads_oppo.FlutterHuaweiAdEventPlugin
import com.huazi.gtads_oppo.LogUtil


@SuppressLint("StaticFieldLeak")
object RewardVideoAd {
    private val TAG = "RewardVideoAd"

    private lateinit var context: Activity
    private var vivoRewardVideoAd: RewardVideoAd? = null

    private var codeId: String? = null
    private var userID: String = ""
    private var rewardName: String = ""
    private var rewardAmount: Int = 0
    private var customData: String = ""


    fun init(context: Activity, params: Map<*, *>) {
        this.context = context
        codeId = params["androidId"] as String
        userID = params["userID"] as String
        rewardName = params["rewardName"] as String
        rewardAmount = params["rewardAmount"] as Int
        customData = params["customData"] as String
        loadRewardVideoAd()
    }

    private fun loadRewardVideoAd() {
        vivoRewardVideoAd = RewardVideoAd(
            context,
            codeId!!,
            rewardVideoAdListener
        )
        vivoRewardVideoAd?.loadAd()
    }

    fun showAd() {
        if (vivoRewardVideoAd == null) {
            var map: MutableMap<String, Any?> =
                mutableMapOf("adType" to "rewardAd", "onAdMethod" to "onUnReady")
            FlutterHuaweiAdEventPlugin.sendContent(map)
            return
        }
        vivoRewardVideoAd?.showAd()
    }

    private var rewardVideoAdListener = object : IRewardVideoAdListener {
        override fun onAdSuccess() {
            LogUtil.e("$TAG  激励广告视频素材缓存成功")
            var map: MutableMap<String, Any?> =
                mutableMapOf("adType" to "rewardAd", "onAdMethod" to "onReady")
            FlutterHuaweiAdEventPlugin.sendContent(map)
        }

        override fun onAdFailed(code:Int,error: String) {
            LogUtil.e("$TAG  广告流程出错 ${code} ${error}")
            var map: MutableMap<String, Any?> = mutableMapOf(
                "adType" to "rewardAd",
                "onAdMethod" to "onFail",
                "code" to code,
                "message" to error
            )
            FlutterHuaweiAdEventPlugin.sendContent(map)
        }

        override fun onVideoPlayError(p0: String?) {

        }

        override fun onVideoPlayClose(p0: Long) {

        }

        override fun onLandingPageOpen() {

        }

        override fun onLandingPageClose() {

        }

        override fun onAdClick(p0: Long) {
            LogUtil.e("$TAG  激励视频广告被点击")
            var map: MutableMap<String, Any?> =
                mutableMapOf("adType" to "rewardAd", "onAdMethod" to "onClick")
            FlutterHuaweiAdEventPlugin.sendContent(map)
        }

        override fun onVideoPlayStart() {
            LogUtil.e("$TAG  激励视频广告页面展示")
            var map: MutableMap<String, Any?> =
                mutableMapOf("adType" to "rewardAd", "onAdMethod" to "onShow")
            FlutterHuaweiAdEventPlugin.sendContent(map)
        }

        override fun onVideoPlayComplete() {
            LogUtil.e("$TAG  激励视频广告被关闭")
            var map: MutableMap<String, Any?> =
                mutableMapOf("adType" to "rewardAd", "onAdMethod" to "onClose")
            FlutterHuaweiAdEventPlugin.sendContent(map)
            vivoRewardVideoAd = null
        }

        override fun onReward(vararg objects: Any?) {
            LogUtil.e("$TAG  激励视频广告激励发放")
            var map: MutableMap<String, Any?> = mutableMapOf(
                "adType" to "rewardAd",
                "onAdMethod" to "onVerify",
                "rewardName" to rewardName,
                "rewardAmount" to rewardAmount
            )
            FlutterHuaweiAdEventPlugin.sendContent(map)
        }

        override fun onAdFailed(p0: String?) {
            ///废弃方法
        }
    }
}
