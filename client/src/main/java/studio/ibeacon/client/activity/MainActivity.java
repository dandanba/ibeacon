package studio.ibeacon.client.activity;

import android.os.Bundle;
import android.widget.TextView;

import studio.ibeacon.client.R;
import studio.ibeacon.client.fragment.ProductsFragment;
import studio.ibeacon.library.Configs;
import studio.ibeacon.library.actiity.BaseActivity;
import studio.ibeacon.library.utils.FragmentUtil;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final TextView title = findViewById(R.id.title);
        title.setText(Configs.product());

        FragmentUtil.replaceFragment(this, R.id.container, ProductsFragment.newInstance());

    }
}
