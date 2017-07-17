package studio.ibeacon.library;

/**
 * Created by wanggeng on 2017/7/13.
 */

public class Configs {
    private final static String[] products = new String[]{"ods"};
    private final static String[] application_ids = new String[]{"MlJq42p1oQRgHfNB5V7zc42j-gzGzoHsz"};
    private final static String[] client_keys = new String[]{"XncJFkQY8M1f8gVIwWtb0bGs"};
    private final static int current = 0;

    public final static String TAG = products[current];

    public final static String product() {
        return products[current];
    }

    public final static String application_id() {
        return application_ids[current];
    }

    public final static String client_key() {
        return client_keys[current];
    }

}
