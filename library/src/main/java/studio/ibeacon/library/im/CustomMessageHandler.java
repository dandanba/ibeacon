package studio.ibeacon.library.im;

import android.util.Log;

import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMMessage;
import com.avos.avoscloud.im.v2.AVIMMessageHandler;
import com.avos.avoscloud.im.v2.messages.AVIMTextMessage;

import org.greenrobot.eventbus.EventBus;

import studio.ibeacon.library.Configs;
import studio.ibeacon.library.event.MessageEvent;


public class CustomMessageHandler extends AVIMMessageHandler {

    @Override
    public void onMessage(AVIMMessage message, AVIMConversation conversation, AVIMClient client) {
        Log.i(Configs.TAG, "onMessage");
        if (message instanceof AVIMTextMessage) {
            final MessageEvent event = new MessageEvent();
            event.text = ((AVIMTextMessage) message).getText();
            event.conversation = conversation;
            EventBus.getDefault().post(event);
        }
    }

    public void onMessageReceipt(AVIMMessage message, AVIMConversation conversation, AVIMClient client) {
    }
}