package studio.ibeacon.server.activity;

import android.os.Bundle;

import studio.ibeacon.library.actiity.BaseActivity;
import studio.ibeacon.library.utils.FragmentUtil;
import studio.ibeacon.server.R;
import studio.ibeacon.server.fragment.ProductsFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentUtil.replaceFragment(this, R.id.container, ProductsFragment.newInstance());
    }


}
