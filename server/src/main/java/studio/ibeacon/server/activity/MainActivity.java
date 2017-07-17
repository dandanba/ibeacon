package studio.ibeacon.server.activity;

import android.os.Bundle;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;

import java.util.List;

import studio.ibeacon.library.Configs;
import studio.ibeacon.library.actiity.BaseActivity;
import studio.ibeacon.library.data.Product;
import studio.ibeacon.library.utils.FragmentUtil;
import studio.ibeacon.server.R;
import studio.ibeacon.server.app.ServerApplication;
import studio.ibeacon.server.fragment.ProductsFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        FragmentUtil.replaceFragment(this, R.id.container, ProductsFragment.newInstance());
        update();
    }


    private void update() {
        AVQuery<Product> query = AVObject.getQuery(Product.class);
        query.whereEqualTo("company", Configs.product());
        query.findInBackground(new FindCallback<Product>() {
            @Override
            public void done(List<Product> results, AVException e) {
                if (e == null) {
                    ServerApplication.getInstance().setProducts(results);
                }
            }
        });
    }
}
