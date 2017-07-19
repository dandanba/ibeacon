package studio.ibeacon.client.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMConversationCallback;
import com.avos.avoscloud.im.v2.messages.AVIMTextMessage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import cn.bingoogolapple.androidcommon.adapter.BGADivider;
import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildClickListener;
import studio.ibeacon.client.R;
import studio.ibeacon.client.adapter.ProductAdapter;
import studio.ibeacon.client.app.ClientApplication;
import studio.ibeacon.library.Configs;
import studio.ibeacon.library.data.Product;
import studio.ibeacon.library.event.MessageEvent;
import studio.ibeacon.library.event.ProductEvent;
import studio.ibeacon.library.utils.PriceUtil;
import studio.ibeacon.library.utils.ToastUtil;


public class ProductsFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private TextView mPrice, mNumber;
    private ViewGroup mPriceLayout;
    private ProductAdapter mProductAdapter;
    private final View.OnClickListener mClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            final String msg = updatePrice(mProductAdapter.getData());
            final AVIMTextMessage text = new AVIMTextMessage();
            text.setText(msg);
            ClientApplication.getInstance().getConversation().sendMessage(text, new AVIMConversationCallback() {
                @Override
                public void done(AVIMException e) {
                    if (e == null) {
                        mPriceLayout.setVisibility(View.GONE);
                        ToastUtil.show(getContext(), "已经通知到前台");
                    }
                }
            });
            Log.i(Configs.TAG, msg);
        }
    };
    private BGAOnItemChildClickListener mItemChildClickListener = new BGAOnItemChildClickListener() {
        @Override
        public void onItemChildClick(ViewGroup parent, View childView, int position) {
            switch (childView.getId()) {
                case R.id.minus_button:
                    mProductAdapter.getData().get(position).number--;
                    if (mProductAdapter.getData().get(position).number < 0) {
                        mProductAdapter.getData().get(position).number = 0;
                    }
                    mProductAdapter.notifyDataSetChanged();
                    updatePrice();
                    break;
                case R.id.plus_button:
                    mProductAdapter.getData().get(position).number++;
                    mProductAdapter.notifyDataSetChanged();
                    updatePrice();
                    break;
                default:
                    break;
            }
        }
    };

    public static ProductsFragment newInstance() {
        ProductsFragment fragment = new ProductsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_products, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        mProductAdapter.setData(ClientApplication.getInstance().getProducts());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDetach() {
        EventBus.getDefault().unregister(this);
        super.onDetach();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onProductEvent(ProductEvent event) {
        mProductAdapter.setData(event.data);
        updatePrice();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        Log.i(Configs.TAG, event.text);
        ToastUtil.show(getContext(), event.text);
    }

    private void initView(View view) {
        mRecyclerView = view.findViewById(R.id.recyclerview);
        mRecyclerView.addItemDecoration(BGADivider.newBitmapDivider());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        mProductAdapter = new ProductAdapter(mRecyclerView);
        mProductAdapter.setOnItemChildClickListener(mItemChildClickListener);
        mRecyclerView.setAdapter(mProductAdapter);

        mPriceLayout = view.findViewById(R.id.price_layout);
        mPrice = view.findViewById(R.id.price);
        mNumber = view.findViewById(R.id.count);

        mPriceLayout.setVisibility(View.GONE);
        view.findViewById(R.id.button).setOnClickListener(mClickListener);
    }

    private void updatePrice() {
        mPriceLayout.setVisibility(View.VISIBLE);

        int total = 0;
        int number = 0;

        final List<Product> products = mProductAdapter.getData();
        final int size = products == null ? 0 : products.size();
        Product product;
        for (int i = 0; i < size; i++) {
            product = products.get(i);
            number += product.number;
            total += product.getPrice();
        }

        mPrice.setText(PriceUtil.toString(total));
        mNumber.setText(String.valueOf(number));
    }

    private String updatePrice(List<Product> products) {
        final StringBuffer sb = new StringBuffer();
        final int size = products == null ? 0 : products.size();
        Product product;
        for (int i = 0; i < size; i++) {
            product = products.get(i);
            if (product.number > 0) {
                if (sb.length() > 0) {
                    sb.append(";");
                }
                sb.append(product.getObjectId());
                sb.append(":");
                sb.append(product.number);
            }
        }
        return sb.toString();
    }
}
