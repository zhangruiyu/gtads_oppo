package com.huazi.gtads_oppo

import android.app.Activity
import android.app.Application
import android.content.Context
import com.heytap.msp.mobad.api.InitParams
import com.heytap.msp.mobad.api.MobAdManager
import com.huazi.gtads_oppo.interstitialad.InterstitialAd
import com.huazi.gtads_oppo.rewardvideoad.RewardVideoAd
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler


/** GtadsVivoPlugin */
class GtadsOppoPlugin : FlutterPlugin, MethodCallHandler, ActivityAware {
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
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, "gtads_oppo")
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
                val initParams: InitParams = InitParams.Builder()
                    .setDebug(debug) //true打开SDK日志，当应用发布Release版本时，必须注释掉这行代码的调用，或者设为false
                    .build()

                /**
                 * 调用这行代码初始化广告SDK
                 */
                MobAdManager.getInstance().init(applicationContext, mediaId, initParams)
                result.success(true)
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
