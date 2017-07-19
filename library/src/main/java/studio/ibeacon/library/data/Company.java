package studio.ibeacon.library.data;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

/**
 * Created by wanggeng on 2017/7/13.
 */
@AVClassName("Company")
public class Company extends AVObject {

    // -------------------- 数据库数据 开始 --------------------------
    public String getcName() {
        return getString("cName");
    }

    public void setcName(String cName) {
        put("cName", cName);
    }

    public String getcShortName() {
        return getString("cShortName");
    }

    public void setcShortName(String cShortName) {
        put("cShortName", cShortName);
    }
    // -------------------- 数据库数据 结束 --------------------------
}
