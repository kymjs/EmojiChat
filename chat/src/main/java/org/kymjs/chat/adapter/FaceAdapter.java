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
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import org.kymjs.chat.R;
import org.kymjs.chat.bean.Faceicon;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 表情区域GridView的适配器
 *
 * @author kymjs (http://www.kymjs.com/)
 */
public class FaceAdapter extends BaseAdapter {
    private List<Faceicon> mDatas;
    private Context context;

    public FaceAdapter(AbsListView view, Collection<Faceicon> mDatas) {
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
            convertView = View.inflate(context, R.layout.chat_item_face, null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = convertView.findViewById(R.id.itemImage);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Glide.with(context).load(mDatas.get(position)).into(viewHolder.imageView);
        return convertView;
    }

    private static class ViewHolder {
        ImageView imageView;
    }
}