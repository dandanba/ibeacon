package studio.ibeacon.server.data;

import com.avos.avoscloud.im.v2.AVIMConversation;


/**
 * Created by wanggeng on 2017/7/17.
 */

public class Product extends studio.ibeacon.library.data.Product {
    public int number;
    public int type;
    public AVIMConversation conversation;

    public int getPrice() {
        return getpPrice() == null ? 0 : Integer.parseInt(getpPrice()) * number;
    }
}
