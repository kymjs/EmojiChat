[![OSL](https://kymjs.com/qiniu/image/logo3.png)](https://www.kymjs.com/works/)  
=================

## EmojiChat简介
一个聊天界面，包括从网络下载大表情并使用，图片发送，文字发送，Emoji表情发送，自定义表情键盘，Emoji表情键盘，仿QQ功能键盘等等……  
*8月1日更新*：每个emoji表情页的最后添加删除图标。  
*8月5日更新*：支持聊天内容超链接高亮以及点击链接跳到相应url中。  
*8月10日更新*：再添加一种html链接高亮，支持在聊天中发送html标签的跳转信息，支持链接样式自定义(链接颜色，下划线，跳转链接的处理)。  
*11月18日*：使用依赖库[emojicon](https://github.com/rockerhieu/emojicon)作为emoji图标显示方案，修复了之前在很老旧版本的手机上会字体读取异常的问题

## 使用方法
1、修改大表情底部图标，见[FaceCategroyAdapter](https://github.com/kymjs/EmojiChat/blob/master/chat/src/main/java/org/kymjs/chat/adapter/FaceCategroyAdapter.java)  
2、大表情下载并添加到表情键盘，在表情键盘创建时传入大表情文件夹的地址，见[ChatActivity的96行](https://github.com/kymjs/EmojiChat/blob/master/chat/src/main/java/org/kymjs/chat/ChatActivity.java)  
3、功能键盘(图片、拍照)修改，见[ChatFunctionFragment](https://github.com/kymjs/EmojiChat/blob/master/chat/src/main/java/org/kymjs/chat/ChatFunctionFragment.java)  
4、聊天键盘的点击事件修改，见[ChatActivity中56行开始监听器内](https://github.com/kymjs/EmojiChat/blob/master/chat/src/main/java/org/kymjs/chat/ChatActivity.java)  
5、聊天类型定义，支持文字，图片，文字+Emoji，大表情(本质是图片)。你可以自己扩展支持定位、视频、语音等。见[ChatAdapter](https://github.com/kymjs/EmojiChat/blob/master/chat/src/main/java/org/kymjs/chat/adapter/ChatAdapter.java)，[KJChatKeyboard](https://github.com/kymjs/EmojiChat/blob/master/chat/src/main/java/org/kymjs/chat/widget/KJChatKeyboard.java)  
6、如果你想测试大表情，请先复制[表情包](https://github.com/kymjs/EmojiChat/tree/master/bigFaceImage)中的chat文件夹(包括其中的全部表情)到SD卡根目录，并修改[ChatActivity的96行](https://github.com/kymjs/EmojiChat/blob/master/chat/src/main/java/org/kymjs/chat/ChatActivity.java)的注释与97行替换     

## 关于
* 更多我的开源项目：[开源实验室开放项目](https://www.kymjs.com/)

## 运行截图
![截图1](http://kymjs.com/qiniu/image/emojichat/Screenshot_1.png)
![截图2](http://kymjs.com/qiniu/image/emojichat/Screenshot_2.png)
![截图3](http://kymjs.com/qiniu/image/emojichat/Screenshot_3.png)
![截图4](http://kymjs.com/qiniu/image/emojichat/Screenshot_4.png)
![截图5](http://kymjs.com/qiniu/image/emojichat/Screenshot_5.png)
![截图6](http://kymjs.com/qiniu/image/emojichat/Screenshot_6.png)

## 开源协议
```
 Copyright (C) 2014, 张涛
 
 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 ```
