package studio.ibeacon.library.actiity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by wanggeng on 2017/7/17.
 */

public class BaseActivity extends AppCompatActivity implements Handler.Callback {
    private Handler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new Handler(this);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }

    public void sendEmptyMessageDelayed(int what, long delayMillis) {
        mHandler.sendEmptyMessageDelayed(what, delayMillis);
    }

    @Override
    public boolean handleMessage(Message message) {
        return false;
    }
}
