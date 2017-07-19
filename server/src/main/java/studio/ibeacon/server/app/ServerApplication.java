package studio.ibeacon.server.app;

import com.avos.avoscloud.im.v2.AVIMClient;

import studio.ibeacon.library.Configs;
import studio.ibeacon.library.app.BaseApplication;

/**
 * Created by wanggeng on 2017/7/17.
 */

public class ServerApplication extends BaseApplication {
    private static ServerApplication sInstance;

    public static ServerApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AVIMClient.getInstance(Configs.product()).open(null);

        sInstance = this;
    }
}
