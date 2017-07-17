package studio.ibeacon.server.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;

import studio.ibeacon.server.R;
import studio.ibeacon.library.actiity.BaseActivity;

public class LogoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

        sendEmptyMessageDelayed(1, 2000);
    }

    @Override
    public boolean handleMessage(Message message) {
        if (message.what == 1) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return true;
        }
        return super.handleMessage(message);
    }
}
