package studio.ibeacon.library.event;

import java.util.List;

import studio.ibeacon.library.data.Product;


/**
 * Created by wanggeng on 2017/7/14.
 */

public class ProductEvent {
    public List<Product> data;

    public ProductEvent(List<Product> data) {
        this.data = data;
    }
}
