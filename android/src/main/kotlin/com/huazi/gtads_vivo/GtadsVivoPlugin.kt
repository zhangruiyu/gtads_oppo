package com.huazi.gtads_vivo

import android.app.Activity
import android.app.Application
import android.content.Context
import com.huazi.gtads_vivo.interstitialad.InterstitialAd
import com.huazi.gtads_vivo.rewardvideoad.RewardVideoAd
import com.vivo.mobilead.manager.VInitCallback
import com.vivo.mobilead.manager.VivoAdManager
import com.vivo.mobilead.model.VAdConfig
import com.vivo.mobilead.unified.base.VivoAdError
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler


/** GtadsVivoPlugin */
class GtadsVivoPlugin : FlutterPlugin, MethodCallHandler, ActivityAware {
    private var applicationContext: Context? = null
    private var mActivity: Activity? = null
    private var mApplication: Application? = null

    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    private lateinit var channel: MethodChannel

    override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        this.applicationContext = flutterPluginBinding.applicationContext
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, "gtads_vivo")
        channel.setMethodCallHandler(this)
        FlutterHuaweiAdEventPlugin().onAttachedToEngine(flutterPluginBinding)
    }

    override fun onAttachedToActivity(binding: ActivityPluginBinding) {
        mApplication = binding.activity.application
        mActivity = binding.activity
//        Log.e("GtadsHuaweiPlugin->","onAttachedToActivity")
//        FlutterTencentAdViewPlugin.registerWith(mFlutterPluginBinding!!,mActivity!!)
    }

    override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
        mApplication = binding.activity.application
        mActivity = binding.activity
//        Log.e("GtadsHuaweiPlugin->","onReattachedToActivityForConfigChanges")
    }

    override fun onDetachedFromActivityForConfigChanges() {
        mActivity = null
        mApplication = null
//        Log.e("GtadsHuaweiPlugin->","onDetachedFromActivityForConfigChanges")
    }

    override fun onDetachedFromActivity() {
        mActivity = null
        mApplication = null
//        Log.e("GtadsHuaweiPlugin->","onDetachedFromActivity")
    }

    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        when (call.method) {
            "init" -> {
                val debug = call.argument<Boolean>("debug")!!
                val mediaId = call.argument<String>("mediaId")!!
                val adConfig = VAdConfig.Builder()
                    .setMediaId(
                        mediaId
                    )
                    .setDebug(debug).build()
                VivoAdManager.getInstance().setAgreePrivacyStrategy(true)

                /**
                 * 开发者需要在用户同意APP的隐私政策之后调用以下代码来初始化Vivo广告联盟SDK
                 */
                VivoAdManager.getInstance().init(mApplication, adConfig, object : VInitCallback {
                    override fun suceess() {
                        result.success(true)
                    }

                    override fun failed(adError: VivoAdError) {
                        result.success(false)
                    }
                })
//                val debug = call.argument<Boolean>("debug")
//                // Initialize Vivo SDK
//                VivoAdConfig.init(applicationContext!!)
//                LogUtil.setAppName("flutter_vivoad")
//                LogUtil.setShow(debug!!)
//                result.success(true)
            }

            "loadInterstitialAD" -> {
                InterstitialAd.init(mActivity!!, call.argument("androidId")!!)
                result.success(true)
            }

            "showInterstitialAD" -> {
                InterstitialAd.showAd()
                result.success(true)
            }

            "loadRewardVideoAd" -> {
                RewardVideoAd.init(mActivity!!, call.arguments as Map<*, *>)
                result.success(true)
            }

            "showRewardVideoAd" -> {
                RewardVideoAd.showAd()
                result.success(true)
            }

            else -> {
                result.notImplemented()
            }
        }
    }

    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }
}
