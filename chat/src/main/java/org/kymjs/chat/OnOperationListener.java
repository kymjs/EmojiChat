package org.kymjs.chat;


import org.kymjs.chat.bean.Emojicon;
import org.kymjs.chat.bean.Faceicon;

/**
 * 表情栏顶部按钮的监听器
 *
 * @author kymjs (http://www.kymjs.com/)
 */
public interface OnOperationListener {

    void send(String content);

    void selectedFace(Faceicon content);

    void selectedEmoji(Emojicon content);

    void selectedFunction(int index);
}
