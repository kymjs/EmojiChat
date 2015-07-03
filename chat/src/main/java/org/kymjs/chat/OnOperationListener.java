package org.kymjs.chat;


import org.kymjs.chat.bean.Emojicon;
import org.kymjs.chat.bean.Faceicon;

/**
 * 表情栏顶部按钮的监听器
 * 
 * @author kymjs (http://www.kymjs.com/)
 */
public interface OnOperationListener {

    public void send(String content);

    public void selectedFace(Faceicon content);
    
    public void selectedEmoji(Emojicon content);
}
