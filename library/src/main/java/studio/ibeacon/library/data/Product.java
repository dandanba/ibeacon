package studio.ibeacon.library.data;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.im.v2.AVIMConversation;

/**
 * Created by wanggeng on 2017/7/13.
 */
@AVClassName("Product")
public class Product extends AVObject {


    public int number;
    public int type;
    public AVIMConversation conversation;
    // -------------------- 数据库数据 开始 --------------------------

    public String getpName() {
        return getString("pName");
    }

    public void setpName(String pName) {
        put("pName", pName);
    }

    public String getpDesc() {
        return getString("pDesc");
    }

    public void setpDesc(String pDesc) {
        put("pDesc", pDesc);
    }

    public String getpPrice() {
        return getString("pPrice");
    }

    public void setpPrice(String pPrice) {
        put("pPrice", pPrice);
    }

    public String getpImgUrl() {
        return getString("pImgUrl");
    }

    public void setpImgUrl(String pImgUrl) {
        put("pImgUrl", pImgUrl);
    }

    public String getpType() {
        return getString("pType");
    }

    public void setpType(String pType) {
        put("pType", pType);
    }

    public String getCompany() {
        return getString("company");
    }

    public void setCompany(String company) {
        put("company", company);
    }

    // -------------------- 数据库数据 结束 --------------------------
    public int getPrice() {
        return getpPrice() == null ? 0 : Integer.parseInt(getpPrice()) * number;
    }
}
