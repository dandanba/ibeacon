package studio.ibeacon.server.event;

import java.util.List;

import studio.ibeacon.server.data.Product;

/**
 * Created by wanggeng on 2017/7/14.
 */

public class ProductEvent {
    public List<Product> data;

    public ProductEvent(List<Product> data) {
        this.data = data;
    }
}
