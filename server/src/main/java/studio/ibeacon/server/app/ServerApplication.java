package studio.ibeacon.server.app;

import com.avos.avoscloud.im.v2.AVIMClient;

import java.util.List;

import studio.ibeacon.library.Configs;
import studio.ibeacon.library.app.BaseApplication;
import studio.ibeacon.library.data.Product;

/**
 * Created by wanggeng on 2017/7/17.
 */

public class ServerApplication extends BaseApplication {
    private static ServerApplication sInstance;
    private List<Product> mProducts;

    public static ServerApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AVIMClient.getInstance(Configs.product()).open(null);
        sInstance = this;
    }

    public List<Product> getProducts() {
        return mProducts;
    }

    public void setProducts(List<Product> products) {
        mProducts = products;
    }
}
