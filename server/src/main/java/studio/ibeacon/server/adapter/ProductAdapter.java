package studio.ibeacon.server.adapter;

import android.support.v7.widget.RecyclerView;

import com.bumptech.glide.Glide;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;
import studio.ibeacon.server.data.Product;
import studio.ibeacon.server.R;

/**
 * Created by wanggeng on 2017/7/14.
 */

public class ProductAdapter extends BGARecyclerViewAdapter<Product> {

    public ProductAdapter(RecyclerView recyclerView) {
        super(recyclerView);
    }

    @Override
    public int getItemViewType(int position) {
        switch (getItem(position).type) {
            case -1:
                return R.layout.layout_title_item;
            case 1:
                return R.layout.layout_price_item;
            default:
                return R.layout.layout_product_item;
        }
    }

    @Override
    protected void setItemChildListener(BGAViewHolderHelper helper, int viewType) {
        super.setItemChildListener(helper, viewType);
        helper.setItemChildClickListener(R.id.finish_button);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, Product model) {
        switch (getItem(position).type) {
            case -1:
                helper.getTextView(R.id.title).setText(model.conversation.getName());
                break;
            case 1:
                break;
            default:
                Glide.with(mContext).load(model.getpImgUrl()).into(helper.getImageView(R.id.image));
                helper.getTextView(R.id.title).setText(model.getpName());
                helper.getTextView(R.id.number).setText(String.valueOf(model.number));
                break;
        }
    }
}