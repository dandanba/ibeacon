package studio.ibeacon.client.adapter;

import android.support.v7.widget.RecyclerView;

import com.bumptech.glide.Glide;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;
import studio.ibeacon.client.R;
import studio.ibeacon.library.data.Product;
import studio.ibeacon.library.utils.PriceUtil;


/**
 * Created by wanggeng on 2017/7/14.
 */

public class ProductAdapter extends BGARecyclerViewAdapter<Product> {

    public ProductAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.layout_product_item);
    }

    @Override
    protected void setItemChildListener(BGAViewHolderHelper helper, int viewType) {
        super.setItemChildListener(helper, viewType);
        helper.setItemChildClickListener(R.id.minus_button);
        helper.setItemChildClickListener(R.id.plus_button);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, Product model) {
        Glide.with(mContext).load(model.getpImgUrl()).into(helper.getImageView(R.id.image));
        helper.getTextView(R.id.title).setText(model.getpName());
        helper.getTextView(R.id.description).setText(model.getpDesc());
        helper.getTextView(R.id.price).setText(PriceUtil.toString(model.getpPrice()));
        helper.getTextView(R.id.number).setText(String.valueOf(model.number));
    }
}
