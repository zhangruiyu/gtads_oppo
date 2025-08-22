import 'dart:async';

import 'package:flutter/services.dart';

import 'flutter_oppo_callback.dart';
import 'flutter_oppoad_code.dart.dart';

/// @Author: gstory
/// @CreateDate: 2021/8/9 9:30 下午
/// @Description: dart类作用描述

const EventChannel OppoAdEventEvent =
    EventChannel("com.huazi.gtads_oppo/adevent");

class FlutterOppoAdStream {
  ///
  /// # 注册stream监听原生返回的信息
  ///
  /// [rewardAdCallBack] 激励广告回调
  ///
  /// [interactionAdCallBack] 插屏广告回调
  ///
  static StreamSubscription initAdStream(
      {FlutterOppoadRewardCallBack? flutterOppoadRewardCallBack,
      FlutterOppoadInteractionCallBack? flutterOppoadInteractionCallBack}) {
    StreamSubscription _adStream =
        OppoAdEventEvent.receiveBroadcastStream().listen((data) {
      switch (data[FlutterOppoadType.adType]) {
        ///激励广告
        case FlutterOppoadType.rewardAd:
          switch (data[FlutterOppoadMethod.onAdMethod]) {
            case FlutterOppoadMethod.onShow:
              if (flutterOppoadRewardCallBack?.onShow != null) {
                flutterOppoadRewardCallBack?.onShow!();
              }
              break;
            case FlutterOppoadMethod.onClose:
              if (flutterOppoadRewardCallBack?.onClose != null) {
                flutterOppoadRewardCallBack?.onClose!();
              }
              break;
            case FlutterOppoadMethod.onFail:
              if (flutterOppoadRewardCallBack?.onFail != null) {
                flutterOppoadRewardCallBack?.onFail!(
                    data["code"], data["message"]);
              }
              break;
            case FlutterOppoadMethod.onClick:
              if (flutterOppoadRewardCallBack?.onClick != null) {
                flutterOppoadRewardCallBack?.onClick!();
              }
              break;
            case FlutterOppoadMethod.onVerify:
              if (flutterOppoadRewardCallBack?.onVerify != null) {
                flutterOppoadRewardCallBack?.onVerify!(
                    data["rewardName"], data["rewardAmount"]);
              }
              break;
            case FlutterOppoadMethod.onFinish:
              if (flutterOppoadRewardCallBack?.onFinish != null) {
                flutterOppoadRewardCallBack?.onFinish!();
              }
              break;
            case FlutterOppoadMethod.onReady:
              if (flutterOppoadRewardCallBack?.onReady != null) {
                flutterOppoadRewardCallBack?.onReady!();
              }
              break;
            case FlutterOppoadMethod.onUnReady:
              if (flutterOppoadRewardCallBack?.onUnReady != null) {
                flutterOppoadRewardCallBack?.onUnReady!();
              }
              break;
            case FlutterOppoadMethod.onExpose:
              if (flutterOppoadRewardCallBack?.onExpose != null) {
                flutterOppoadRewardCallBack?.onExpose!();
              }
              break;
            case FlutterOppoadMethod.onECPM:
              if (flutterOppoadRewardCallBack?.onECPM != null) {
                flutterOppoadRewardCallBack?.onECPM!(
                    data["ecpmLevel"], data["ecpm"]);
              }
              break;
          }
          break;

        ///插屏广告
        case FlutterOppoadType.interactAd:
          switch (data[FlutterOppoadMethod.onAdMethod]) {
            case FlutterOppoadMethod.onShow:
              if (flutterOppoadInteractionCallBack?.onShow != null) {
                flutterOppoadInteractionCallBack?.onShow!();
              }
              break;
            case FlutterOppoadMethod.onClose:
              if (flutterOppoadInteractionCallBack?.onClose != null) {
                flutterOppoadInteractionCallBack?.onClose!();
              }
              break;
            case FlutterOppoadMethod.onFail:
              if (flutterOppoadInteractionCallBack?.onFail != null) {
                flutterOppoadInteractionCallBack?.onFail!(
                    data["code"], data["message"]);
              }
              break;
            case FlutterOppoadMethod.onClick:
              if (flutterOppoadInteractionCallBack?.onClick != null) {
                flutterOppoadInteractionCallBack?.onClick!();
              }
              break;
            case FlutterOppoadMethod.onExpose:
              if (flutterOppoadInteractionCallBack?.onExpose != null) {
                flutterOppoadInteractionCallBack?.onExpose!();
              }
              break;
            case FlutterOppoadMethod.onReady:
              if (flutterOppoadInteractionCallBack?.onReady != null) {
                flutterOppoadInteractionCallBack?.onReady!();
              }
              break;
            case FlutterOppoadMethod.onUnReady:
              if (flutterOppoadInteractionCallBack?.onUnReady != null) {
                flutterOppoadInteractionCallBack?.onUnReady!();
              }
              break;
            case FlutterOppoadMethod.onVerify:
              if (flutterOppoadInteractionCallBack?.onVerify != null) {
                flutterOppoadInteractionCallBack?.onVerify!("", 0);
              }
              break;
            case FlutterOppoadMethod.onECPM:
              if (flutterOppoadInteractionCallBack?.onECPM != null) {
                flutterOppoadInteractionCallBack?.onECPM!(
                    data["ecpmLevel"], data["ecpm"]);
              }
              break;
          }
          break;
      }
    });
    return _adStream;
  }

  static void deleteAdStream(StreamSubscription stream) {
    stream.cancel();
  }
}
