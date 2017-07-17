package studio.ibeacon.library.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by wanggeng on 2017/7/17.
 */

public class FragmentUtil {

    public static void replaceFragment(AppCompatActivity appCompatActivity, int layout, Fragment fragment) {
        FragmentManager fm = appCompatActivity.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(layout, fragment);
        ft.commit();
    }

}
