[![OSL](http://www.kymjs.com/image/logo_s.png)](http://www.kymjs.com/works/)
=================

## EmojiChat简介
一个聊天界面，包括从网络下载大表情并使用，图片发送，文字发送，Emoji表情发送，自定义表情键盘，Emoji表情键盘，仿QQ功能键盘等等……  
*8月1日更新*：每个emoji表情页的最后添加删除图标。  
*8月5日更新*：支持聊天内容超链接高亮以及点击链接跳到相应url中。  

## 使用方法
1、修改大表情底部图标，见[FaceCategroyAdapter](https://github.com/kymjs/EmojiChat/blob/master/chat/src/main/java/org/kymjs/chat/adapter/FaceCategroyAdapter.java)  
2、大表情下载并添加到表情键盘，在表情键盘创建时传入大表情文件夹的地址，见[ChatActivity的96行](https://github.com/kymjs/EmojiChat/blob/master/chat/src/main/java/org/kymjs/chat/ChatActivity.java)  
3、功能键盘(图片、拍照)修改，见[ChatFunctionFragment](https://github.com/kymjs/EmojiChat/blob/master/chat/src/main/java/org/kymjs/chat/ChatFunctionFragment.java)  
4、聊天键盘的点击事件修改，见[ChatActivity中56行开始监听器内](https://github.com/kymjs/EmojiChat/blob/master/chat/src/main/java/org/kymjs/chat/ChatActivity.java)  
5、聊天类型定义，支持文字，图片，文字+Emoji，大表情(本质是图片)。你可以自己扩展支持定位、视频、语音等。见[ChatAdapter](https://github.com/kymjs/EmojiChat/blob/master/chat/src/main/java/org/kymjs/chat/adapter/ChatAdapter.java)，[KJChatKeyboard](https://github.com/kymjs/EmojiChat/blob/master/chat/src/main/java/org/kymjs/chat/widget/KJChatKeyboard.java)  
6、如果你想测试大表情，请先复制[表情包](https://github.com/kymjs/EmojiChat/tree/master/bigFaceImage)中的chat文件夹(包括其中的全部表情)到SD卡根目录，并修改[ChatActivity的96行](https://github.com/kymjs/EmojiChat/blob/master/chat/src/main/java/org/kymjs/chat/ChatActivity.java)的注释与97行替换    

##附加说明
也许在你惊叹本项目实现emoji表情输入之简易的同时，又在感慨需要引入一个12.2M大小的表情包的成本太过大(某些旧版本手机无法支持这么大的表情包)。是的，如果你希望能使用一个精简版的表情包，可以私聊我(QQ766136833)。它是我从八十七万行纯文本中一行一行精简下来的，精简版表情包大小是2.6M，包含165个常用emoji图标(原项目包含822个)。   

## 关于
自我介绍：[张涛就是我](http://blog.kymjs.com/about)  
* QQ 群[257053751](http://jq.qq.com/?_wv=1027&k=WoM2Aa)(开发者群1)，[201055521](http://jq.qq.com/?_wv=1027&k=MBVdpK)(开发者群2)<br>
* 更多我的开源项目：[开源实验室](http://www.kymjs.com/)
* blog：http://blog.kymjs.com/  

##运行截图
![截图1](https://github.com/kymjs/EmojiChat/blob/master/screen_shots/Screenshot_1.png)
![截图2](https://github.com/kymjs/EmojiChat/blob/master/screen_shots/Screenshot_2.png)
![截图3](https://github.com/kymjs/EmojiChat/blob/master/screen_shots/Screenshot_3.png)
![截图4](https://github.com/kymjs/EmojiChat/blob/master/screen_shots/Screenshot_4.png)
![截图5](https://github.com/kymjs/EmojiChat/blob/master/screen_shots/Screenshot_5.png)

##开源协议
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
