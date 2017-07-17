package studio.ibeacon.library.event;

import com.avos.avoscloud.im.v2.AVIMConversation;

public class MessageEvent {
    public String text;
    public AVIMConversation conversation;

    public MessageEvent() {
    }
}
