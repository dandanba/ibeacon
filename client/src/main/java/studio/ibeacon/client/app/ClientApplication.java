package studio.ibeacon.client.app;

import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;

import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;
import com.avos.avoscloud.im.v2.callback.AVIMConversationCreatedCallback;

import java.util.Arrays;

import studio.ibeacon.library.Configs;
import studio.ibeacon.library.app.BaseApplication;
import studio.ibeacon.library.utils.PreferenceUtil;

/**
 * Created by wanggeng on 2017/7/17.
 */

public class ClientApplication extends BaseApplication {
    private static ClientApplication sInstance;
    private AVIMConversation mConversation;
    private String mUserId;
    private String mConversationId;
    private final AVIMClientCallback mAVIMClientCallback = new AVIMClientCallback() {
        @Override
        public void done(AVIMClient client, AVIMException e) {
            if (e == null) {
                if (TextUtils.isEmpty(mConversationId)) {
                    client.createConversation(Arrays.asList(Configs.product()), Configs.product() + ":" + mUserId, null, new AVIMConversationCreatedCallback() { // createConversation
                        @Override
                        public void done(AVIMConversation conversation, AVIMException e) {
                            if (e == null) {
                                mConversation = conversation;

                                PreferenceUtil.putString(ClientApplication.this, "conversation_id", mConversation.getConversationId());
                            } else {
                                Log.e(Configs.TAG, "create conversation", e);
                            }
                        }
                    });
                } else {
                    mConversation = client.getConversation(mConversationId);
                }
            }
        }
    };

    public static ClientApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mConversationId = PreferenceUtil.getString(this, "conversation_id");
        mUserId = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);

        AVIMClient.getInstance(mUserId).open(mAVIMClientCallback);

        sInstance = this;
    }

    public AVIMConversation getConversation() {
        return mConversation;
    }
}
