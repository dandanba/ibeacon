package studio.ibeacon.library.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.multidex.MultiDexApplication;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.im.v2.AVIMMessageManager;

import studio.ibeacon.library.Configs;
import studio.ibeacon.library.data.Product;
import studio.ibeacon.library.im.CustomMessageHandler;

/**
 * Created by wanggeng on 2017/7/17.
 */

public class BaseApplication extends MultiDexApplication {

    private SharedPreferences mPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        mPreferences = getSharedPreferences(Configs.product(), Context.MODE_PRIVATE);

        AVObject.registerSubclass(Product.class);
        AVOSCloud.initialize(this, Configs.application_id(), Configs.client_key());        // 初始化参数依次为 this, AppId, AppKey
        AVIMMessageManager.registerDefaultMessageHandler(new CustomMessageHandler());        //注册默认的消息处理逻辑
    }

    public SharedPreferences getPreferences() {
        return mPreferences;
    }


}
