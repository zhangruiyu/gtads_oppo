package com.huazi.gtads_oppo.rewardvideoad

import android.annotation.SuppressLint
import android.app.Activity
import com.vivo.mobilead.unified.base.AdParams
import com.vivo.mobilead.unified.base.VivoAdError
import com.vivo.mobilead.unified.reward.UnifiedVivoRewardVideoAd
import com.vivo.mobilead.unified.reward.UnifiedVivoRewardVideoAdListener
import com.huazi.gtads_oppo.FlutterHuaweiAdEventPlugin
import com.huazi.gtads_oppo.LogUtil


@SuppressLint("StaticFieldLeak")
object RewardVideoAd {
    private val TAG = "RewardVideoAd"

    private lateinit var context: Activity
    private var vivoRewardVideoAd: UnifiedVivoRewardVideoAd? = null

    private var codeId: String? = null
    private var userID: String = ""
    private var rewardName: String = ""
    private var rewardAmount: Int = 0
    private var customData: String = ""


    fun init(context: Activity, params: Map<*, *>) {
        RewardVideoAd.context = context
        codeId = params["androidId"] as String
        userID = params["userID"] as String
        rewardName = params["rewardName"] as String
        rewardAmount = params["rewardAmount"] as Int
        customData = params["customData"] as String
        loadRewardVideoAd()
    }

    private fun loadRewardVideoAd() {
        val builder = AdParams.Builder(codeId!!)
        // Vivo SDK doesn't have a direct way to set user ID and custom data
        // We'll use the reward name and amount in the onRewardVerify callback
        vivoRewardVideoAd = UnifiedVivoRewardVideoAd(context, builder.build(), rewardVideoAdListener)
        vivoRewardVideoAd?.loadAd()
    }

    fun showAd() {
        if (vivoRewardVideoAd == null) {
            var map: MutableMap<String, Any?> =
                mutableMapOf("adType" to "rewardAd", "onAdMethod" to "onUnReady")
            FlutterHuaweiAdEventPlugin.sendContent(map)
            return
        }
        vivoRewardVideoAd?.showAd(context)
    }

    private var rewardVideoAdListener = object : UnifiedVivoRewardVideoAdListener {
        override fun onAdReady() {
            LogUtil.e("$TAG  激励广告视频素材缓存成功")
            var map: MutableMap<String, Any?> =
                mutableMapOf("adType" to "rewardAd", "onAdMethod" to "onReady")
            FlutterHuaweiAdEventPlugin.sendContent(map)
        }

        override fun onAdFailed(error: VivoAdError) {
            LogUtil.e("$TAG  广告流程出错 ${error.code}")
            var map: MutableMap<String, Any?> = mutableMapOf(
                "adType" to "rewardAd",
                "onAdMethod" to "onFail",
                "code" to error.code,
                "message" to error.msg
            )
            FlutterHuaweiAdEventPlugin.sendContent(map)
        }

        override fun onAdClick() {
            LogUtil.e("$TAG  激励视频广告被点击")
            var map: MutableMap<String, Any?> =
                mutableMapOf("adType" to "rewardAd", "onAdMethod" to "onClick")
            FlutterHuaweiAdEventPlugin.sendContent(map)
        }

        override fun onAdShow() {
            LogUtil.e("$TAG  激励视频广告页面展示")
            var map: MutableMap<String, Any?> =
                mutableMapOf("adType" to "rewardAd", "onAdMethod" to "onShow")
            FlutterHuaweiAdEventPlugin.sendContent(map)
        }

        override fun onAdClose() {
            LogUtil.e("$TAG  激励视频广告被关闭")
            var map: MutableMap<String, Any?> =
                mutableMapOf("adType" to "rewardAd", "onAdMethod" to "onClose")
            FlutterHuaweiAdEventPlugin.sendContent(map)
            vivoRewardVideoAd = null
        }

        override fun onRewardVerify() {
            LogUtil.e("$TAG  激励视频广告激励发放")
            var map: MutableMap<String, Any?> = mutableMapOf(
                "adType" to "rewardAd",
                "onAdMethod" to "onVerify",
                "rewardName" to rewardName,
                "rewardAmount" to rewardAmount
            )
            FlutterHuaweiAdEventPlugin.sendContent(map)
        }
    }
}
