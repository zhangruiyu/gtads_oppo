/// 描述：
/// @author guozi
/// @e-mail gstory0404@gmail.com
/// @time   2020/3/11

///数据类型
class FlutterHuaweiadType {
  static const String adType = "adType";

  ///激励广告
  static const String rewardAd = "rewardAd";

  ///插屏广告
  static const String interactAd = "interactAd";
}

class FlutterHuaweiadMethod {
  ///stream中 广告方法
  static const String onAdMethod = "onAdMethod";

  ///广告加载状态 view使用
  ///显示view
  static const String onShow = "onShow";

  ///广告曝光
  static const String onExpose = "onExpose";

  ///加载失败
  static const String onFail = "onFail";

  ///点击
  static const String onClick = "onClick";

  ///视频播放
  static const String onVideoPlay = "onVideoPlay";

  ///视频暂停
  static const String onVideoPause = "onVideoPause";

  ///视频结束
  static const String onVideoStop = "onVideoStop";

  ///倒计时结束
  static const String onFinish = "onFinish";

  ///加载超时
  static const String onTimeOut = "onTimeOut";

  ///广告关闭
  static const String onClose = "onClose";

  ///广告奖励校验
  static const String onVerify = "onVerify";

  ///广告预加载完成
  static const String onReady = "onReady";

  ///广告未预加载
  static const String onUnReady = "onUnReady";

  ///倒计时
  static const String onADTick = "onADTick";
  ///竞价
  static const String onECPM = "onECPM";
}

///个性化广告
class FlutterHuaweiadPersonalized {
  ///屏蔽个性化推荐广告
  static const int close = 1;

  ///不屏蔽个性化推荐广告
  static const int show = 0;
}
