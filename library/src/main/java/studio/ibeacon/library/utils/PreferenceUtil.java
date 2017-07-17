package studio.ibeacon.library.utils;

import android.content.SharedPreferences;

import studio.ibeacon.library.app.BaseApplication;

/**
 * Created by wanggeng on 2017/7/14.
 */

public class PreferenceUtil {

    public static void putString(BaseApplication application, String key, String value) {
        SharedPreferences.Editor editor = application.getPreferences().edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getString(BaseApplication application, String key) {
        return application.getPreferences().getString(key, "");
    }

}
