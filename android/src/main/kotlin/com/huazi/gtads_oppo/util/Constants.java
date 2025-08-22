package com.huazi.gtads_oppo.util;

/**
 * @author 11101035
 */
public final class Constants {
    public interface IntentKey {
        String POSITION_ID = "position_id";
    }

    public interface ConfigureKey {
        String MEDIA_ID = "media_id";
        String SPLASH_POSITION_ID = "splash_position_id";
        String BANNER_POSITION_ID = "banner_position_id";
        String INTERSTITIAL_POSITION_ID = "interstitial_position_id";
        String NATIVE_STREAM_POSITION_ID = "native_stream_position_id";
        String VIDEO_POSITION_ID = "video_position_id";
        String FLOAT_ICON = "float_icon";
        String SPLASH_AD_TIME = "splash_ad_time";
        String BANNER_AD_TIME = "banner_ad_time";
        String APP_TITLE = "app_title";
        String APP_DESC = "app_desc";
        String HOT_SPLASH = "hot_splash";
        String VIDEO_INTERSTITIAL_POSITION_ID = "interstitial_video_position_id";
        String IS_CAN_USE_LOCATION = "is_can_use_location";
        String IS_CAN_USE_PHONE_STATE = "is_can_use_phone_state";
        String IS_CAN_USE_WIFI_STATE = "is_can_use_wifi_state";
        String IS_CAN_USE_WRITE_EXTERNAL = "is_can_use_write_external";
        String GET_LOCATION_LAT = "get_location_lat";
        String GET_LOCATION_LNG = "get_location_lng";
        String GET_IMEI = "get_imei";

        String GET_OAID = "get_oaid";
        String IS_CAN_PERSONAL_RECOMMEND = "personal_recommend";
    }


    public interface DefaultConfigValue {
        /**
         * 以下ID需填入自己在广告后台申请的相关id
         */
        String MEDIA_ID = "03ad601d82b64f8e9b4939259b47c26e";
        String SPLASH_POSITION_ID = "8a8f010319f44df39548642741fcf894";
        String BANNER_POSITION_ID = "3766cae79fe24dde890230abdf4fc5b2";
        String INTERSTITIAL_POSITION_ID = "747f550e01eb4b87878264f293bfefe5";
        String VIDEO_INTERSTITIAL_POSITION_ID = "0f001688d45145da9ed8fdf8f5e24eb5";
        String NATIVE_STREAM_POSITION_ID = "a45047f634d24753911ac976e4fa7ae8";
        String VIDEO_POSITION_ID = "fbf56eb035f1481dafcc6bd62954ab3f";
        String FLOAT_ICON = "9954870b8b2f4d729ccaffab31241ad7";
        /**
         * 原生模板化-横板纯图
         **/
        String NATIVE_EXPRESS_MATERIAL_ID = "359f63373ba34df89d569e7090718d04";

        /**
         * 原生模板化-三图模板
         **/
        String NATIVE_EXPRESS_MATERIAL_GROUP_ID = "7faf11f9c8a84faeb5b8407edb12d359";

        /**
         * 原生模板化-左文右图
         **/
        String NATIVE_EXPRESS_MATERIAL_RIGHT_ID = "70d40dfdb7944ad7a0e827487d50ab9c";

        /**
         * 原生模板化-左图右文
         **/
        String NATIVE_EXPRESS_MATERIAL_LEFT_ID = "233d61e29feb43b3ab957c39cc88879a";

        /**
         * 原生模板化-上图下文
         **/
        String NATIVE_EXPRESS_MATERIAL_TOP_ID = "9802782c25904370b6ebd51954088422";

        /**
         * 原生模板化-上文下图
         **/
        String NATIVE_EXPRESS_MATERIAL_BOTTOM_ID = "389db372d5314f99b54addd351b394f8";
        /**
         * 原生模板化-竖版纯图
         **/
        String NATIVE_EXPRESS_VERTICAL_MATERIAL_ID = "fe648600b46d4eff9f7408c3aaf2a3d3";

        int SPLASH_AD_TIME = 3;
        int BANNER_AD_TIME = 15;
        String APP_TITLE = "开心消消乐";
        String APP_DESC = "娱乐休闲首选游戏";
        int HOT_SPLASH = 1; //-1: 关、1:竖屏、2:横屏

        boolean IS_CAN_USE_LOCATION = true;
        boolean IS_CAN_USE_PHONE_STATE = true;
        boolean IS_CAN_USE_WIFI_STATE = true;
        boolean IS_CAN_USE_WRITE_EXTERNAL = true;
        boolean IS_CAN_PERSONAL_RECOMMEND = true;
        String GET_LOCATION_LAT = "198.35";
        String GET_LOCATION_LNG = "568.35";
        String GET_IMEI = "129948456089";

        String GET_OAID = "dajdjakdjsadeueu";
    }
}
