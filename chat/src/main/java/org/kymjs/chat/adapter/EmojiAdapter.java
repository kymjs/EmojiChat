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
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.kymjs.chat.R;
import org.kymjs.chat.bean.Emojicon;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * emoji表情界面gridview适配器
 *
 * @author kymjs (http://www.kymjs.com/) on 6/8/15.
 */
public class EmojiAdapter extends BaseAdapter {
    private List<Emojicon> mDatas;
    private Context context;

    public EmojiAdapter(AbsListView view, Collection<Emojicon> mDatas) {
        super();
        this.mDatas = new ArrayList<>(mDatas);
        context = view.getContext();
    }

    @Override
    public int getCount() {
        if (mDatas == null) {
            return 0;
        } else {
            return mDatas.size();
        }
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.chat_item_emoji, null);
            viewHolder = new ViewHolder();
            viewHolder.itemTvEmoji = convertView.findViewById(R.id.itemEmoji);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.itemTvEmoji.setText(mDatas.get(position).getValue());
        return convertView;
    }

    private static class ViewHolder {
        TextView itemTvEmoji;
    }
}
