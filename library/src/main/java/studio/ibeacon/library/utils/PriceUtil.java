package studio.ibeacon.library.utils;

/**
 * Created by wanggeng on 2017/7/14.
 */

public class PriceUtil {

    public static String toString(String price) {
        return String.format("￥ %.2f", Integer.parseInt(price) / 100.0f);
    }

    public static String toString(int price) {
        return String.format("￥ %.2f", price / 100.0f);
    }

}
