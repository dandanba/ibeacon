package studio.ibeacon.server.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMConversationCallback;
import com.avos.avoscloud.im.v2.messages.AVIMTextMessage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.androidcommon.adapter.BGADivider;
import cn.bingoogolapple.androidcommon.adapter.BGAOnItemChildClickListener;
import studio.ibeacon.library.data.Product;
import studio.ibeacon.library.event.MessageEvent;
import studio.ibeacon.library.utils.ToastUtil;
import studio.ibeacon.server.R;
import studio.ibeacon.server.adapter.ProductAdapter;
import studio.ibeacon.server.app.ServerApplication;


public class ProductsFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private ProductAdapter mProductAdapter;

    private BGAOnItemChildClickListener mItemChildClickListener = new BGAOnItemChildClickListener() {
        @Override
        public void onItemChildClick(ViewGroup parent, View childView, int position) {
            switch (childView.getId()) {
                case R.id.finish_button:

                    int number = 0;
                    Product item = mProductAdapter.getItem(position);
                    mProductAdapter.removeItem(position);
                    position--;

                    while (item.type != -1) {
                        number += item.number;
                        mProductAdapter.removeItem(position);
                        position--;
                        item = mProductAdapter.getItem(position);
                    }

                    final String msg = "number:" + number;
                    final AVIMTextMessage text = new AVIMTextMessage();
                    text.setText(msg);

                    item.conversation.sendMessage(text, new AVIMConversationCallback() {
                        @Override
                        public void done(AVIMException e) {
                            if (e == null) {
                                ToastUtil.show(getContext(), "已经通知到用户");
                            }
                        }
                    });
                    mProductAdapter.removeItem(position);
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
    public void onMessageEvent(MessageEvent event) {
        final String text = event.text;

        final String[] sa = text.split(";");
        final int size = sa.length;
        final List<Product> results = new ArrayList<>();

        final Product head = new Product();
        head.type = -1;
        head.conversation = event.conversation;
        results.add(head);

        // ---------- body start ----------
        int number;
        String objectId;
        String[] is;
        for (int i = 0; i < size; i++) {
            is = sa[i].split(":");

            objectId = is[0];
            number = Integer.parseInt(is[1]);

            Product product = getProduct(objectId);
            product.number = number;

            results.add(product);
        }
        // ---------- body end ----------

        final Product foot = new Product();
        foot.type = 1;
        results.add(foot);

        mProductAdapter.addMoreData(results);
    }

    private Product getProduct(String objectId) {
        List<Product> mProducts = ServerApplication.getInstance().getProducts();
        int size = mProducts.size();
        Product product;
        for (int i = 0; i < size; i++) {
            product = mProducts.get(i);
            if (objectId.equals(product.getObjectId())) {
                return product;
            }
        }
        return null;
    }

    private void initView(View view) {
        mRecyclerView = view.findViewById(R.id.recyclerview);
        mRecyclerView.addItemDecoration(BGADivider.newBitmapDivider());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        mProductAdapter = new ProductAdapter(mRecyclerView);
        mProductAdapter.setOnItemChildClickListener(mItemChildClickListener);
        mRecyclerView.setAdapter(mProductAdapter);
    }

}
