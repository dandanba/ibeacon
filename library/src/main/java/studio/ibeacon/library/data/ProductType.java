package studio.ibeacon.library.data;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

/**
 * Created by wanggeng on 2017/7/13.
 */
@AVClassName("ProductType")
public class ProductType extends AVObject {

    // -------------------- 数据库数据 开始 --------------------------
    public String gettName() {
        return getString("tName");
    }

    public void settName(String tName) {
        put("tName", tName);
    }
    // -------------------- 数据库数据 结束 --------------------------
}
