package studio.ibeacon.library.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by wanggeng on 2017/7/14.
 */

public class ToastUtil {

    public static void show(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

}
