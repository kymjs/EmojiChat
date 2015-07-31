/*
 * Copyright (c) 2015, 张涛.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
    
    void selectedBackSpace(Emojicon back);

    void selectedFunction(int index);
}
