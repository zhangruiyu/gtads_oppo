import 'dart:async';

import 'package:gtads/gtads.dart';
import 'package:gtads_vivo/src/gtads_vivo_plugin.dart';

import 'flutter_vivo_callback.dart';
import 'flutter_vivoad_stream.dart';

class GTAdsVivoProvider extends GTAdsProvider {
  GTAdsVivoProvider(String alias,String mediaId) : super(alias, mediaId, '');

  @override
  Future<bool> initAd(bool isDebug) async {
    return MethodChannelGtadsHuawei.init(isDebug, androidId!);
  }

  @override
  StreamSubscription? insertAd(
      GTAdsCode adCode, bool isFull, GTAdsCallBack? callBack) {
    StreamSubscription? stream = null;
    stream = FlutterHuaweiAdStream.initAdStream(
      flutterHuaweiadInteractionCallBack: FlutterHuaweiadInteractionCallBack(
        onShow: () {
          if (callBack != null && callBack.onShow != null) {
            callBack.onShow!(adCode);
          }
        },
        onClick: () {
          if (callBack != null && callBack.onClick != null) {
            callBack.onClick!(adCode);
          }
        },
        onFail: (code, message) {
          if (callBack != null && callBack.onFail != null) {
            callBack.onFail!(adCode, message);
          }
        },
        onClose: () {
          if (callBack != null && callBack.onClose != null) {
            callBack.onClose!(adCode);
          }
        },
        onReady: () async {
          await MethodChannelGtadsHuawei.showUnifiedInterstitialAD();
        },
        onUnReady: () {
          if (callBack != null && callBack.onFail != null) {
            callBack.onFail!(adCode, "广告未准备就绪");
          }
        },
        onVerify: (rewardName, rewardAmount) {
          if (callBack != null && callBack.onVerify != null) {
            callBack.onVerify!(adCode, true, "", rewardName, rewardAmount);
          }
        },
      ),
    );
    MethodChannelGtadsHuawei.loadInterstitialAD(
        androidId: adCode.androidId ?? "", ohosId: adCode.ohosId ?? "");
    return stream;
  }

  @override
  StreamSubscription? rewardAd(
      GTAdsCode adCode,
      String rewardName,
      int rewardAmount,
      String userId,
      String customData,
      GTAdsCallBack? callBack) {
    StreamSubscription? stream;
    stream = FlutterHuaweiAdStream.initAdStream(
      //激励广告
      flutterHuaweiadRewardCallBack: FlutterHuaweiadRewardCallBack(onShow: () {
        if (callBack != null && callBack.onShow != null) {
          callBack.onShow!(adCode);
        }
      }, onClick: () {
        if (callBack != null && callBack.onClick != null) {
          callBack.onClick!(adCode);
        }
      }, onFail: (code, message) {
        if (callBack != null && callBack.onFail != null) {
          callBack.onFail!(adCode, message);
        }
      }, onClose: () {
        if (callBack != null && callBack.onClose != null) {
          callBack.onClose!(adCode);
        }
      }, onReady: () async {
        await MethodChannelGtadsHuawei.showRewardVideoAd();
      }, onUnReady: () {
        if (callBack != null && callBack.onFail != null) {
          callBack.onFail!(adCode, "激励广告预加载未准备就绪");
        }
      }, onVerify: (rewardName, rewardAmount) {
        if (callBack != null && callBack.onVerify != null) {
          callBack.onVerify!(adCode, true, "", rewardName, rewardAmount);
        }
      }, onFinish: () {
        if (callBack != null && callBack.onFinish != null) {
          callBack.onFinish!(adCode);
        }
      }),
    );
    MethodChannelGtadsHuawei.loadRewardVideoAd(
      //android广告id
      androidId: adCode.androidId ?? "",
      //用户id
      userID: userId,
      //奖励
      rewardName: rewardName,
      //奖励数
      rewardAmount: rewardAmount,
      //扩展参数 服务器回调使用
      customData: customData,
    );
    return stream;
  }
}
