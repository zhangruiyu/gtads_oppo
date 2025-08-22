import 'package:flutter/services.dart';

/// An implementation of [GtadsOppoPlatform] that uses method channels.
class MethodChannelGtadsOppo {
  static const methodChannel = MethodChannel('gtads_oppo');

  static Future<bool> init(bool debug, String mediaId) async {
    final result =
        await methodChannel.invokeMethod<bool>('init', {'debug': debug,'mediaId':mediaId});
    return result!;
  }

  static Future<bool> loadInterstitialAD({
    required String androidId,
    required String ohosId,
  }) async {
    final result =
        await methodChannel.invokeMethod<bool>('loadInterstitialAD', {
      "ohosId": ohosId,
      "androidId": androidId,
    });
    return result!;
  }

  static Future<bool> showUnifiedInterstitialAD() async {
    return await methodChannel.invokeMethod("showInterstitialAD");
  }

  static Future<bool> loadRewardVideoAd({
    required String androidId,
    required String rewardName,
    required int rewardAmount,
    required String userID,
    String? customData,
  }) async {
    return await methodChannel.invokeMethod("loadRewardVideoAd", {
      "androidId": androidId,
      "rewardName": rewardName,
      "rewardAmount": rewardAmount,
      "userID": userID,
      "customData": customData ?? "",
    });
  }

  ///
  /// # 显示激励广告
  ///
  static Future<bool> showRewardVideoAd() async {
    return await methodChannel.invokeMethod("showRewardVideoAd");
  }
}
