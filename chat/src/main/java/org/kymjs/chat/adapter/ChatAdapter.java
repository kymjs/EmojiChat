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
package org.kymjs.chat.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.kymjs.chat.ChatActivity.OnChatItemClickListener;
import org.kymjs.chat.R;
import org.kymjs.chat.bean.Message;
import org.kymjs.kjframe.KJBitmap;
import org.kymjs.kjframe.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author kymjs (http://www.kymjs.com/) on 6/8/15.
 */
public class ChatAdapter extends BaseAdapter {

    private final Context cxt;
    private List<Message> datas = null;
    private KJBitmap kjb;
    private OnChatItemClickListener listener;
    private Typeface emojitf;

    public ChatAdapter(Context cxt, List<Message> datas, OnChatItemClickListener listener) {
        this.cxt = cxt;
        if (datas == null) {
            datas = new ArrayList<Message>(0);
        }
        this.datas = datas;
        kjb = new KJBitmap();
        this.listener = listener;
        try {
            emojitf = Typeface.createFromAsset(cxt.getAssets(), "fonts/emoji.ttf");
        } catch (RuntimeException e) {
        }
    }

    public void refresh(List<Message> datas) {
        if (datas == null) {
            datas = new ArrayList<Message>(0);
        }
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return datas.get(position).getIsSend() ? 1 : 0;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(final int position, View v, ViewGroup parent) {
        final ViewHolder holder;
        final Message data = datas.get(position);
        if (v == null) {
            holder = new ViewHolder();
            if (data.getIsSend()) {
                v = View.inflate(cxt, R.layout.chat_item_list_right, null);
            } else {
                v = View.inflate(cxt, R.layout.chat_item_list_left, null);
            }
            holder.layout_content = (RelativeLayout) v.findViewById(R.id.chat_item_layout_content);
            holder.img_avatar = (ImageView) v.findViewById(R.id.chat_item_avatar);
            holder.img_chatimage = (ImageView) v.findViewById(R.id.chat_item_content_image);
            holder.img_sendfail = (ImageView) v.findViewById(R.id.chat_item_fail);
            holder.progress = (ProgressBar) v.findViewById(R.id.chat_item_progress);
            holder.tv_chatcontent = (TextView) v.findViewById(R.id.chat_item_content_text);
            holder.tv_date = (TextView) v.findViewById(R.id.chat_item_date);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        holder.tv_date.setText(StringUtils.friendlyTime(StringUtils.getDataTime("yyyy-MM-dd " +
                "HH:mm:ss")));
        holder.tv_date.setVisibility(View.VISIBLE);

        if (emojitf != null) {
            holder.tv_chatcontent.setTypeface(emojitf);
        }

        //如果是文本类型，则隐藏图片，如果是图片则隐藏文本
        if (data.getType() == Message.MSG_TYPE_TEXT) {
            holder.img_chatimage.setVisibility(View.GONE);
            holder.tv_chatcontent.setVisibility(View.VISIBLE);
            holder.tv_chatcontent = handleText(holder.tv_chatcontent, data.getContent());
        } else {
            holder.tv_chatcontent.setVisibility(View.GONE);
            holder.img_chatimage.setVisibility(View.VISIBLE);

            //如果内存缓存中有要显示的图片，且要显示的图片不是holder复用的图片，则什么也不做，否则显示一张加载中的图片
            if (kjb.getMemoryCache(data.getContent()) != null && data.getContent() != null &&
                    data.getContent().equals(holder.img_chatimage.getTag())) {
            } else {
                holder.img_chatimage.setImageResource(R.drawable.default_head);
            }
            kjb.display(holder.img_chatimage, data.getContent(), 300, 300);
        }

        //如果是表情或图片，则不显示气泡，如果是图片则显示气泡
        if (data.getType() != Message.MSG_TYPE_TEXT) {
            holder.layout_content.setBackgroundResource(android.R.color.transparent);
        } else {
            if (data.getIsSend()) {
                holder.layout_content.setBackgroundResource(R.drawable.chat_to_bg_selector);
            } else {
                holder.layout_content.setBackgroundResource(R.drawable.chat_from_bg_selector);
            }
        }

        //显示头像
        if (data.getIsSend()) {
            kjb.display(holder.img_avatar, data.getFromUserAvatar());
        } else {
            kjb.display(holder.img_avatar, data.getToUserAvatar());
        }

        if (listener != null) {
            holder.tv_chatcontent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onTextClick(position);
                }
            });
            holder.img_chatimage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (data.getType()) {
                        case Message.MSG_TYPE_PHOTO:
                            listener.onPhotoClick(position);
                            break;
                        case Message.MSG_TYPE_FACE:
                            listener.onFaceClick(position);
                            break;
                    }
                }
            });
        }

        //消息发送的状态
        switch (data.getState()) {
            case Message.MSG_STATE_FAIL:
                holder.progress.setVisibility(View.GONE);
                holder.img_sendfail.setVisibility(View.VISIBLE);
                break;
            case Message.MSG_STATE_SUCCESS:
                holder.progress.setVisibility(View.GONE);
                holder.img_sendfail.setVisibility(View.GONE);
                break;
            case Message.MSG_STATE_SENDING:
                holder.progress.setVisibility(View.VISIBLE);
                holder.img_sendfail.setVisibility(View.GONE);
                break;
        }
        return v;
    }

    static class ViewHolder {
        TextView tv_date;
        ImageView img_avatar;
        TextView tv_chatcontent;
        ImageView img_chatimage;
        ImageView img_sendfail;
        ProgressBar progress;
        RelativeLayout layout_content;
    }

    /**
     * 让TextView自动解析URL并高亮设置点击链接(链接的开头可以没有空格，但是结尾必须有空格)
     * Note:深深的体会到，写一个正则不容易啊
     *
     * @param tv      TextView
     * @param content 要高亮的内容
     * @return 已经解析之后的TextView
     */
    public static TextView handleText(TextView tv, String content) {
        SpannableString sp = new SpannableString(content);
        //妈蛋，又碰上一个坑，在Android中的\p{Alnum}和Java中的\p{Alnum}不是同一个值，非得要我换成[a-zA-Z0-9]才行
        //这鸟问题折腾了我四五个小时，明明Java工程中运行良好，写到android中就不正常
        Pattern pattern = Pattern.compile("(http|https|ftp|svn)://([a-zA-Z0-9]+[/?.?])" +
                "+[a-zA-Z0-9]*\\??([a-zA-Z0-9]*=[a-zA-Z0-9]*&?)*");
        Matcher matcher = pattern.matcher(content);

        while (matcher.find()) {
            String url = matcher.group();
            int start = content.indexOf(url);
            if (start >= 0) {
                int end = start + url.length();
                sp.setSpan(new URLSpan(url), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }

        tv.setText(sp);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        return tv;
    }
}
