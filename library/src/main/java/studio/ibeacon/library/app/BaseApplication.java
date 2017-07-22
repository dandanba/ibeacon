package studio.ibeacon.library.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.multidex.MultiDexApplication;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.im.v2.AVIMMessageManager;

import java.util.List;

import studio.ibeacon.library.Configs;
import studio.ibeacon.library.data.Company;
import studio.ibeacon.library.data.Product;
import studio.ibeacon.library.data.ProductType;
import studio.ibeacon.library.im.CustomMessageHandler;

/**
 * Created by wanggeng on 2017/7/17.
 */

public class BaseApplication extends MultiDexApplication {
    private Company mCompany;
    private List<ProductType> mProductTypes;
    private List<Product> mProducts;
    private SharedPreferences mPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        mPreferences = getSharedPreferences(Configs.product(), Context.MODE_PRIVATE);

        AVObject.registerSubclass(Company.class);
        AVObject.registerSubclass(ProductType.class);
        AVObject.registerSubclass(Product.class);
        AVOSCloud.initialize(this, Configs.application_id(), Configs.client_key());        // 初始化参数依次为 this, AppId, AppKey
        AVIMMessageManager.registerDefaultMessageHandler(new CustomMessageHandler());        //注册默认的消息处理逻辑

        update();
    }

    private void update() {
        updateCompany();
    }

    public SharedPreferences getPreferences() {
        return mPreferences;
    }

    private void updateCompany() {
        AVQuery<Company> query = AVObject.getQuery(Company.class);
        query.whereEqualTo("cShortName", Configs.product());
        query.findInBackground(new FindCallback<Company>() {
            @Override
            public void done(List<Company> results, AVException e) {
                if (e == null) {
                    mCompany = results.get(0);
                    updateData();
                }
            }
        });
    }

    public void updateData() {
        updateProductTypes();
        updateProducts();
    }

    public void updateProductTypes() {
        AVQuery<ProductType> query = AVObject.getQuery(ProductType.class);
        query.whereEqualTo("company", mCompany.getObjectId());
        query.findInBackground(new FindCallback<ProductType>() {
            @Override
            public void done(List<ProductType> results, AVException e) {
                if (e == null) {
                    mProductTypes = results;
                }
            }
        });
    }

    public void updateProducts() {
        AVQuery<Product> query = AVObject.getQuery(Product.class);
        query.whereEqualTo("company", mCompany.getObjectId());
        query.findInBackground(new FindCallback<Product>() {
            @Override
            public void done(List<Product> results, AVException e) {
                if (e == null) {
                    mProducts = results;
                }
            }
        });
    }

    public Company getCompany() {
        return mCompany;
    }

    public List<ProductType> getProductTypes() {
        return mProductTypes;
    }

    public List<Product> getProducts() {
        return mProducts;
    }
}
